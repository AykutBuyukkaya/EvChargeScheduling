package com.buyukkaya.chargeschedulingservice.domain.mapper;

import com.buyukkaya.chargeschedulingservice.domain.model.dto.ScheduledChargeDto;
import com.buyukkaya.chargeschedulingservice.domain.model.entity.ScheduledCharge;
import com.buyukkaya.chargeschedulingservice.domain.model.request.ChargeSchedulingRequest;
import org.mapstruct.Mapper;

@Mapper
public interface ScheduledChargeMapper {

    ScheduledCharge fromChargeSchedulingRequest(ChargeSchedulingRequest chargeSchedulingRequest);

    ScheduledChargeDto toDto(ScheduledCharge scheduledCharge);

}
