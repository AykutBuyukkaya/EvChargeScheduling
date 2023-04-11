package com.buyukkaya.chargeschedulingservice.domain.strategy;

import com.buyukkaya.chargeschedulingservice.domain.model.entity.ScheduledCharge;
import com.buyukkaya.chargeschedulingservice.domain.model.request.ChargeSchedulingRequest;

import java.util.List;

public interface SchedulingStrategy {

    ScheduledCharge scheduleCharge(ChargeSchedulingRequest chargeSchedulingRequest);

    List<ScheduledCharge> scheduleMultipleCharges(List<ChargeSchedulingRequest> chargeSchedulingRequestList);

}
