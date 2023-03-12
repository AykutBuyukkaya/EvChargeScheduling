package com.buyukkaya.chargeschedulingservice.domain.service.impl;


import com.buyukkaya.chargeschedulingservice.domain.exception.ChargeSchedulingException;
import com.buyukkaya.chargeschedulingservice.domain.mapper.DataCollectorMapper;
import com.buyukkaya.chargeschedulingservice.domain.mapper.ScheduledChargeMapper;
import com.buyukkaya.chargeschedulingservice.domain.model.entity.ScheduledCharge;
import com.buyukkaya.chargeschedulingservice.domain.model.request.ChargeSchedulingRequest;
import com.buyukkaya.chargeschedulingservice.domain.model.response.DataCollectorResponse;
import com.buyukkaya.chargeschedulingservice.domain.service.ChargeSchedulingService;
import com.buyukkaya.chargeschedulingservice.domain.service.DataCollectorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ChargeSchedulingServiceImpl implements ChargeSchedulingService {

    @Qualifier("defaultChain")
    private final DataCollectorService dataCollectorService;
    private final DataCollectorMapper dataCollectorMapper;
    private final ScheduledChargeMapper scheduledChargeMapper;

    public ChargeSchedulingServiceImpl(DataCollectorService dataCollectorService, DataCollectorMapper dataCollectorMapper, ScheduledChargeMapper scheduledChargeMapper) {
        this.dataCollectorService = dataCollectorService;
        this.dataCollectorMapper = dataCollectorMapper;
        this.scheduledChargeMapper = scheduledChargeMapper;
    }

    @Override
    public DataCollectorResponse scheduleCharge(ChargeSchedulingRequest request) {

        ScheduledCharge scheduledCharge = scheduledChargeMapper.fromChargeSchedulingRequest(request);

        calculateOptimalChargingPower(dataCollectorService.collectData(dataCollectorMapper.fromChargeSchedulingRequest(request)), scheduledCharge);

        return dataCollectorResponse;


    }

    private void calculateOptimalChargingPower(DataCollectorResponse dataCollectorResponse, ScheduledCharge scheduledCharge) {

        final Map<String, Double> evACChargingPowerMap = dataCollectorResponse.getEvDataServiceResponse().getEv().getAcCharger().getPowerPerChargingPoint();
        final List<String> chargingStationOutputList = dataCollectorResponse.getChargingStationServiceResponse().getChargingStation().getAvailablePowerOutputs();

        scheduledCharge.setChargingPower(Double.parseDouble(evACChargingPowerMap.entrySet()
                .stream()
                .filter(stringDoubleEntry -> chargingStationOutputList.contains(stringDoubleEntry.getKey()))
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .orElseThrow(ChargeSchedulingException::new)
                .getKey()));

    }

    private void calcualteRequiredTimeToCharge(DataCollectorResponse dataCollectorResponse, ScheduledCharge scheduledCharge) {

        final Double batteryCapacity = dataCollectorResponse.getEvDataServiceResponse().getEv().getUsableBatterySize();

        final Double requiredChargeInkWH = ((scheduledCharge.getExpectedSoC() - scheduledCharge.getCurrentSoC()) / 100) * batteryCapacity;
        final Double requiredTimeToChargeInHours = requiredChargeInkWH / scheduledCharge.getChargingPower();

        if (requiredTimeToChargeInHours < ChronoUnit.HOURS.between(scheduledCharge.getDepartureTime(), scheduledCharge.getArrivalTime())) {
            throw new ChargeSchedulingException("Could not access expected SoC in given time period. Please change expected SoC.");
        }


        scheduledCharge.setRequiredTimeToChargeInHours(requiredTimeToChargeInHours);


    }

}
