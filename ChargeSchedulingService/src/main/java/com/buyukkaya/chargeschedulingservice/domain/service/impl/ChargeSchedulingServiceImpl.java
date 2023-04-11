package com.buyukkaya.chargeschedulingservice.domain.service.impl;


import com.buyukkaya.chargeschedulingservice.domain.mapper.ScheduledChargeMapper;
import com.buyukkaya.chargeschedulingservice.domain.model.dto.ScheduledChargeDto;
import com.buyukkaya.chargeschedulingservice.domain.model.request.ChargeSchedulingRequest;
import com.buyukkaya.chargeschedulingservice.domain.service.ChargeSchedulingService;
import com.buyukkaya.chargeschedulingservice.domain.strategy.SchedulingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ChargeSchedulingServiceImpl implements ChargeSchedulingService {

    private final SchedulingStrategy schedulingStrategy;
    private final SchedulingStrategy prioritySchedulingStrategy;
    private final ScheduledChargeMapper scheduledChargeMapper;

    public ChargeSchedulingServiceImpl(@Qualifier("minimalPricing") SchedulingStrategy schedulingStrategy, @Qualifier("priorityScheduling") SchedulingStrategy prioritySchedulingStrategy, ScheduledChargeMapper scheduledChargeMapper) {
        this.schedulingStrategy = schedulingStrategy;
        this.prioritySchedulingStrategy = prioritySchedulingStrategy;
        this.scheduledChargeMapper = scheduledChargeMapper;
    }


    @Override
    public ScheduledChargeDto scheduleCharge(ChargeSchedulingRequest request) {

        return scheduledChargeMapper.toDto(schedulingStrategy.scheduleCharge(request));

    }

    @Override
    public List<ScheduledChargeDto> scheduleChargePeakShaving(List<ChargeSchedulingRequest> chargeSchedulingRequestList) {

        return scheduledChargeMapper.toDtoList(prioritySchedulingStrategy.scheduleMultipleCharges(chargeSchedulingRequestList));

    }

}
