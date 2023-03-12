package com.buyukkaya.chargeschedulingservice.domain.service;

import com.buyukkaya.chargeschedulingservice.domain.model.request.ChargeSchedulingRequest;
import com.buyukkaya.chargeschedulingservice.domain.model.response.DataCollectorResponse;

public interface ChargeSchedulingService {

    DataCollectorResponse scheduleCharge(ChargeSchedulingRequest request);

}
