package com.buyukkaya.chargeschedulingservice.domain.strategy.impl;

import com.buyukkaya.chargeschedulingservice.domain.exception.ChargeSchedulingException;
import com.buyukkaya.chargeschedulingservice.domain.mapper.DataCollectorMapper;
import com.buyukkaya.chargeschedulingservice.domain.mapper.ScheduledChargeMapper;
import com.buyukkaya.chargeschedulingservice.domain.model.entity.ScheduledCharge;
import com.buyukkaya.chargeschedulingservice.domain.model.enums.ScheduledChargeStatus;
import com.buyukkaya.chargeschedulingservice.domain.model.request.ChargeSchedulingRequest;
import com.buyukkaya.chargeschedulingservice.domain.model.response.DataCollectorResponse;
import com.buyukkaya.chargeschedulingservice.domain.service.collector.DataCollectorService;
import com.buyukkaya.chargeschedulingservice.domain.strategy.SchedulingStrategy;
import com.buyukkaya.chargeschedulingservice.domain.util.CommonFunctions;
import com.buyukkaya.chargeschedulingservice.domain.util.PeakShavingPriorityScoreCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Slf4j
@Component("priorityScheduling")
@Scope("prototype")
public class PeakShavingSchedulingStrategy implements SchedulingStrategy {

    @Value("${update.charging-station-occupation}")
    private String occupationStatusUpdateEndpoint;

    private final DataCollectorService dataCollectorService;
    private final DataCollectorMapper dataCollectorMapper;
    private final ScheduledChargeMapper scheduledChargeMapper;
    private final PeakShavingPriorityScoreCalculator peakShavingPriorityScoreCalculator;
    private final RestTemplate restTemplate;


    public PeakShavingSchedulingStrategy(@Qualifier("peakShavingChain") DataCollectorService dataCollectorService, DataCollectorMapper dataCollectorMapper, ScheduledChargeMapper scheduledChargeMapper, PeakShavingPriorityScoreCalculator peakShavingPriorityScoreCalculator, RestTemplate restTemplate) {
        this.dataCollectorService = dataCollectorService;
        this.dataCollectorMapper = dataCollectorMapper;
        this.scheduledChargeMapper = scheduledChargeMapper;
        this.peakShavingPriorityScoreCalculator = peakShavingPriorityScoreCalculator;
        this.restTemplate = restTemplate;
    }

    @Override
    public ScheduledCharge scheduleCharge(ChargeSchedulingRequest chargeSchedulingRequest) {

        ScheduledCharge scheduledCharge = scheduledChargeMapper.fromChargeSchedulingRequest(chargeSchedulingRequest);

        //Initial status of the charging operation is marked as not fit.
        scheduledCharge.setStatus(ScheduledChargeStatus.NOT_FIT);

        final DataCollectorResponse dataCollectorResponse = dataCollectorService.collectData(dataCollectorMapper.fromChargeSchedulingRequest(chargeSchedulingRequest));

        Map<String, Double> evACChargingPowerMap = dataCollectorResponse.getEvDataServiceResponse().getEv().getAcCharger().getPowerPerChargingPoint();
        final Map<LocalTime, Double> powerUsageLimit = dataCollectorResponse.getChargingStationServiceResponse().getChargingStation().getPowerUsageLimit();
        final Map<LocalTime, Double> powerUsage = dataCollectorResponse.getChargingStationServiceResponse().getChargingStation().getPowerUsage();

        Double tempChargingPower;


        while (!evACChargingPowerMap.isEmpty()) {

            tempChargingPower = evACChargingPowerMap.entrySet().stream().max(Comparator.comparingDouble(Map.Entry::getValue))
                    .map(Map.Entry::getValue)
                    .orElseThrow(ChargeSchedulingException::new);
            scheduledCharge.setChargingPower(tempChargingPower);
            CommonFunctions.calculateRequiredTimeToCharge(dataCollectorResponse, scheduledCharge);

            for (LocalTime ii = scheduledCharge.getArrivalTime(); ii.plusMinutes(scheduledCharge.getRequiredTimeToChargeInMinutes()).isBefore(scheduledCharge.getDepartureTime()); ii = ii.plusMinutes(1)) {

                boolean chargingPossible = true;

                for (LocalTime jj = ii; jj.isBefore(jj.plusMinutes(scheduledCharge.getRequiredTimeToChargeInMinutes())); jj = jj.plusMinutes(1)) {

                    if (powerUsage.get(jj) + tempChargingPower >= powerUsageLimit.get(jj)) {
                        chargingPossible = false;
                        break;
                    }

                }

                if (chargingPossible) {
                    scheduledCharge.setChargeStartTime(ii);
                    scheduledCharge.setChargeEndTime(ii.plusMinutes(scheduledCharge.getRequiredTimeToChargeInMinutes()));
                    scheduledCharge.setStatus(ScheduledChargeStatus.SUCCESSFUL);
                    try {
                        restTemplate.put(occupationStatusUpdateEndpoint, scheduledChargeMapper.toUpdateOccupationStatusRequest(scheduledCharge));
                    } catch (Exception e) {
                        log.error("Error during peak shaving strategy, charging station occupation failed: ", e);
                        throw new ChargeSchedulingException("Charging Station service connection error.");
                    }
                    return scheduledCharge;
                }

            }
            Double finalTempChargingPower = tempChargingPower;
            evACChargingPowerMap.entrySet().removeIf(stringDoubleEntry -> stringDoubleEntry.getValue().equals(finalTempChargingPower));
        }
        return scheduledCharge;
    }

    @Override
    public List<ScheduledCharge> scheduleMultipleCharges(List<ChargeSchedulingRequest> chargeSchedulingRequestList) {

        chargeSchedulingRequestList = chargeSchedulingRequestList.stream()
                .map(peakShavingPriorityScoreCalculator::calculatePriorityScore)
                .sorted(Comparator.comparing(ChargeSchedulingRequest::getPriorityScore).reversed())
                .toList();

        return chargeSchedulingRequestList.stream().map(this::scheduleCharge).toList();

    }


}
