package com.buyukkaya.chargeschedulingservice.domain.service;

import com.buyukkaya.chargeschedulingservice.domain.model.entity.ScheduledCharge;
import com.buyukkaya.chargeschedulingservice.domain.model.request.ChargeSchedulingRequest;

public interface SchedulingStrategy {

    ScheduledCharge scheduleCharge(ChargeSchedulingRequest chargeSchedulingRequest);

}
