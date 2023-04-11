package com.buyukkaya.chargeschedulingservice.domain.mapper;

import com.buyukkaya.chargeschedulingservice.domain.model.dto.ScheduledChargeDto;
import com.buyukkaya.chargeschedulingservice.domain.model.entity.ScheduledCharge;
import com.buyukkaya.chargeschedulingservice.domain.model.request.ChargeSchedulingRequest;
import com.buyukkaya.chargeschedulingservice.domain.model.request.UpdateOccupationStatusRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ScheduledChargeMapper {

    List<ScheduledCharge> fromChargeSchedulingRequestList(List<ChargeSchedulingRequest> chargeSchedulingRequestList);
    ScheduledCharge fromChargeSchedulingRequest(ChargeSchedulingRequest chargeSchedulingRequest);

    List<ScheduledChargeDto> toDtoList(List<ScheduledCharge> scheduledChargeList);

    ScheduledChargeDto toDto(ScheduledCharge scheduledCharge);
    UpdateOccupationStatusRequest toUpdateOccupationStatusRequest(ScheduledCharge scheduledCharge);

}
