package com.buyukkaya.chargeschedulingservice.domain.service;

import com.buyukkaya.chargeschedulingservice.domain.model.dto.ScheduledChargeDto;
import com.buyukkaya.chargeschedulingservice.domain.model.request.ChargeSchedulingRequest;

public interface ChargeSchedulingService {

    ScheduledChargeDto scheduleCharge(ChargeSchedulingRequest request);

}
