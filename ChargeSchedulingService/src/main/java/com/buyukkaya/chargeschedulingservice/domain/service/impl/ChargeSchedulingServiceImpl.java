package com.buyukkaya.chargeschedulingservice.domain.service.impl;


import com.buyukkaya.chargeschedulingservice.domain.mapper.ScheduledChargeMapper;
import com.buyukkaya.chargeschedulingservice.domain.model.dto.ScheduledChargeDto;
import com.buyukkaya.chargeschedulingservice.domain.model.entity.ScheduledCharge;
import com.buyukkaya.chargeschedulingservice.domain.model.request.ChargeSchedulingRequest;
import com.buyukkaya.chargeschedulingservice.domain.model.response.DataCollectorResponse;
import com.buyukkaya.chargeschedulingservice.domain.service.ChargeSchedulingService;
import com.buyukkaya.chargeschedulingservice.domain.service.SchedulingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChargeSchedulingServiceImpl implements ChargeSchedulingService {

    private final SchedulingStrategy schedulingStrategy;
    private final ScheduledChargeMapper scheduledChargeMapper;

    public ChargeSchedulingServiceImpl(SchedulingStrategy schedulingStrategy, ScheduledChargeMapper scheduledChargeMapper) {
        this.schedulingStrategy = schedulingStrategy;
        this.scheduledChargeMapper = scheduledChargeMapper;
    }


    @Override
    public ScheduledChargeDto scheduleCharge(ChargeSchedulingRequest request) {

        return scheduledChargeMapper.toDto(schedulingStrategy.scheduleCharge(request));

    }

}
