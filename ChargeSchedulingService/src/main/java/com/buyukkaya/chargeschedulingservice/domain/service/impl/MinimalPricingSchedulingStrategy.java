package com.buyukkaya.chargeschedulingservice.domain.service.impl;

import com.buyukkaya.chargeschedulingservice.domain.exception.ChargeSchedulingException;
import com.buyukkaya.chargeschedulingservice.domain.mapper.DataCollectorMapper;
import com.buyukkaya.chargeschedulingservice.domain.mapper.ScheduledChargeMapper;
import com.buyukkaya.chargeschedulingservice.domain.model.dto.ElectricityPriceDto;
import com.buyukkaya.chargeschedulingservice.domain.model.entity.ScheduledCharge;
import com.buyukkaya.chargeschedulingservice.domain.model.request.ChargeSchedulingRequest;
import com.buyukkaya.chargeschedulingservice.domain.model.response.DataCollectorResponse;
import com.buyukkaya.chargeschedulingservice.domain.service.DataCollectorService;
import com.buyukkaya.chargeschedulingservice.domain.service.SchedulingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class MinimalPricingSchedulingStrategy implements SchedulingStrategy {

    @Qualifier("defaultChain")
    private final DataCollectorService dataCollectorService;
    private final DataCollectorMapper dataCollectorMapper;

    private final ScheduledChargeMapper scheduledChargeMapper;

    public MinimalPricingSchedulingStrategy(DataCollectorService dataCollectorService, DataCollectorMapper dataCollectorMapper, ScheduledChargeMapper scheduledChargeMapper) {
        this.dataCollectorService = dataCollectorService;
        this.dataCollectorMapper = dataCollectorMapper;
        this.scheduledChargeMapper = scheduledChargeMapper;
    }

    @Override
    public ScheduledCharge scheduleCharge(ChargeSchedulingRequest chargeSchedulingRequest) {

        ScheduledCharge scheduledCharge = scheduledChargeMapper.fromChargeSchedulingRequest(chargeSchedulingRequest);
        final DataCollectorResponse dataCollectorResponse = dataCollectorService.collectData(dataCollectorMapper.fromChargeSchedulingRequest(chargeSchedulingRequest));
        calculateOptimalChargingPower(dataCollectorResponse, scheduledCharge);
        calculateRequiredTimeToCharge(dataCollectorResponse, scheduledCharge);
        findOptimalPricing(dataCollectorResponse, scheduledCharge);

        return scheduledCharge;
    }

    private void calculateOptimalChargingPower(DataCollectorResponse dataCollectorResponse, ScheduledCharge scheduledCharge) {

        final Map<String, Double> evACChargingPowerMap = dataCollectorResponse.getEvDataServiceResponse().getEv().getAcCharger().getPowerPerChargingPoint();
        final List<String> chargingStationOutputList = dataCollectorResponse.getChargingStationServiceResponse().getChargingStation().getAvailablePowerOutputs();

        scheduledCharge.setChargingPower(evACChargingPowerMap.entrySet()
                .stream()
                .filter(stringDoubleEntry -> chargingStationOutputList.contains(stringDoubleEntry.getKey()))
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .orElseThrow(ChargeSchedulingException::new)
                .getValue());

    }

    private void calculateRequiredTimeToCharge(DataCollectorResponse dataCollectorResponse, ScheduledCharge scheduledCharge) {

        final Double batteryCapacity = dataCollectorResponse.getEvDataServiceResponse().getEv().getUsableBatterySize();
        final Double requiredChargingPercentage = (scheduledCharge.getExpectedSoC() - scheduledCharge.getCurrentSoC()) / 100;

        final Double requiredChargeInkWH = requiredChargingPercentage * batteryCapacity;
        final long requiredTimeToChargeInMinutes = Math.round(60 * (requiredChargeInkWH / scheduledCharge.getChargingPower()));

        if (requiredTimeToChargeInMinutes < ChronoUnit.MINUTES.between(scheduledCharge.getDepartureTime(), scheduledCharge.getArrivalTime())) {
            throw new ChargeSchedulingException("Could not access expected SoC in given time period. Please change expected SoC.");
        }

        scheduledCharge.setRequiredTimeToChargeInMinutes(requiredTimeToChargeInMinutes);

    }

    private void findOptimalPricing(DataCollectorResponse dataCollectorResponse, ScheduledCharge scheduledCharge) {

        final Long requiredTimeToChargeInMinutes = scheduledCharge.getRequiredTimeToChargeInMinutes();
        final List<ElectricityPriceDto> electricityPriceDtoList = dataCollectorResponse.getElectricityPricingServiceResponse().getElectricityPricingDataList();
        final Double chargingPower = scheduledCharge.getChargingPower();
        LocalDateTime minIndex = null;
        Double minChargingCost = null;

        for (LocalDateTime ll = scheduledCharge.getArrivalTime(); ll.plusMinutes(requiredTimeToChargeInMinutes).isBefore(scheduledCharge.getDepartureTime()); ll = ll.plusMinutes(1)) {
            LocalDateTime finalLl = ll;
            LocalDateTime localMinIndex = ll;


            double localMinPrice = electricityPriceDtoList.stream().filter(electricityPriceDto -> electricityPriceDto.getDate().isAfter(finalLl) &&
                            electricityPriceDto.getDate().isBefore(finalLl.plusMinutes(requiredTimeToChargeInMinutes)))
                    .mapToDouble(value -> value.getPrice() * (chargingPower / 60)).sum();

            if (minChargingCost == null || localMinPrice < minChargingCost) {
                minChargingCost = localMinPrice;
                minIndex = localMinIndex;
            }
        }

        scheduledCharge.setChargeStartTime(minIndex);
        scheduledCharge.setChargeEndTime(minIndex.plusMinutes(requiredTimeToChargeInMinutes));
        scheduledCharge.setChargingCost(minChargingCost);

    }

}
