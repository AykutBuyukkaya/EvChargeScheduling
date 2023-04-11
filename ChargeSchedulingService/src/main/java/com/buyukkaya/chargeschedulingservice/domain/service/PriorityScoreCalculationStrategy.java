package com.buyukkaya.chargeschedulingservice.domain.service;

import com.buyukkaya.chargeschedulingservice.domain.model.request.ChargeSchedulingRequest;

public interface PriorityScoreCalculationStrategy {

    ChargeSchedulingRequest calculatePriorityScore(ChargeSchedulingRequest chargeSchedulingRequest);
}
