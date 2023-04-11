package com.buyukkaya.chargeschedulingservice.domain.util;

import com.buyukkaya.chargeschedulingservice.domain.model.request.ChargeSchedulingRequest;
import com.buyukkaya.chargeschedulingservice.domain.service.PriorityScoreCalculationStrategy;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;

import static com.buyukkaya.chargeschedulingservice.domain.util.Constants.ARRIVAL_DEPARTURE_TIME_DIFF_CONSTANT;
import static com.buyukkaya.chargeschedulingservice.domain.util.Constants.SOC_LEVEL_CONSTANT;

@Component
public class PeakShavingPriorityScoreCalculator implements PriorityScoreCalculationStrategy {
    @Override
    public ChargeSchedulingRequest calculatePriorityScore(ChargeSchedulingRequest chargeSchedulingRequest) {

        chargeSchedulingRequest.setPriorityScore(
                (1.4 *  ARRIVAL_DEPARTURE_TIME_DIFF_CONSTANT / ChronoUnit.MINUTES.between(chargeSchedulingRequest.getArrivalTime(), chargeSchedulingRequest.getDepartureTime())) +
                        (1.20 * SOC_LEVEL_CONSTANT / chargeSchedulingRequest.getCurrentSoC())
        );

        return chargeSchedulingRequest;

    }
}
