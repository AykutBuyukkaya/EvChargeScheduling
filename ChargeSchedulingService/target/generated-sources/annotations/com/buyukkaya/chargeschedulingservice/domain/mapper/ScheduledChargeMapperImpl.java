package com.buyukkaya.chargeschedulingservice.domain.mapper;

import com.buyukkaya.chargeschedulingservice.domain.model.dto.ScheduledChargeDto;
import com.buyukkaya.chargeschedulingservice.domain.model.entity.ScheduledCharge;
import com.buyukkaya.chargeschedulingservice.domain.model.request.ChargeSchedulingRequest;
import com.buyukkaya.chargeschedulingservice.domain.model.request.UpdateOccupationStatusRequest;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-10T22:06:45+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class ScheduledChargeMapperImpl implements ScheduledChargeMapper {

    @Override
    public List<ScheduledCharge> fromChargeSchedulingRequestList(List<ChargeSchedulingRequest> chargeSchedulingRequestList) {
        if ( chargeSchedulingRequestList == null ) {
            return null;
        }

        List<ScheduledCharge> list = new ArrayList<ScheduledCharge>( chargeSchedulingRequestList.size() );
        for ( ChargeSchedulingRequest chargeSchedulingRequest : chargeSchedulingRequestList ) {
            list.add( fromChargeSchedulingRequest( chargeSchedulingRequest ) );
        }

        return list;
    }

    @Override
    public ScheduledCharge fromChargeSchedulingRequest(ChargeSchedulingRequest chargeSchedulingRequest) {
        if ( chargeSchedulingRequest == null ) {
            return null;
        }

        ScheduledCharge.ScheduledChargeBuilder scheduledCharge = ScheduledCharge.builder();

        scheduledCharge.evTypeId( chargeSchedulingRequest.getEvTypeId() );
        scheduledCharge.chargingStationId( chargeSchedulingRequest.getChargingStationId() );
        scheduledCharge.arrivalTime( chargeSchedulingRequest.getArrivalTime() );
        scheduledCharge.departureTime( chargeSchedulingRequest.getDepartureTime() );
        scheduledCharge.currentSoC( chargeSchedulingRequest.getCurrentSoC() );
        scheduledCharge.expectedSoC( chargeSchedulingRequest.getExpectedSoC() );
        scheduledCharge.priorityScore( chargeSchedulingRequest.getPriorityScore() );
        scheduledCharge.vehicleName( chargeSchedulingRequest.getVehicleName() );
        scheduledCharge.chargingStationName( chargeSchedulingRequest.getChargingStationName() );

        return scheduledCharge.build();
    }

    @Override
    public List<ScheduledChargeDto> toDtoList(List<ScheduledCharge> scheduledChargeList) {
        if ( scheduledChargeList == null ) {
            return null;
        }

        List<ScheduledChargeDto> list = new ArrayList<ScheduledChargeDto>( scheduledChargeList.size() );
        for ( ScheduledCharge scheduledCharge : scheduledChargeList ) {
            list.add( toDto( scheduledCharge ) );
        }

        return list;
    }

    @Override
    public ScheduledChargeDto toDto(ScheduledCharge scheduledCharge) {
        if ( scheduledCharge == null ) {
            return null;
        }

        ScheduledChargeDto.ScheduledChargeDtoBuilder scheduledChargeDto = ScheduledChargeDto.builder();

        scheduledChargeDto.id( scheduledCharge.getId() );
        scheduledChargeDto.evId( scheduledCharge.getEvId() );
        scheduledChargeDto.evTypeId( scheduledCharge.getEvTypeId() );
        scheduledChargeDto.chargingStationId( scheduledCharge.getChargingStationId() );
        scheduledChargeDto.arrivalTime( scheduledCharge.getArrivalTime() );
        scheduledChargeDto.departureTime( scheduledCharge.getDepartureTime() );
        scheduledChargeDto.currentSoC( scheduledCharge.getCurrentSoC() );
        scheduledChargeDto.expectedSoC( scheduledCharge.getExpectedSoC() );
        scheduledChargeDto.chargingPower( scheduledCharge.getChargingPower() );
        scheduledChargeDto.requiredTimeToChargeInMinutes( scheduledCharge.getRequiredTimeToChargeInMinutes() );
        scheduledChargeDto.chargeStartTime( scheduledCharge.getChargeStartTime() );
        scheduledChargeDto.chargeEndTime( scheduledCharge.getChargeEndTime() );
        scheduledChargeDto.chargingCost( scheduledCharge.getChargingCost() );
        scheduledChargeDto.priorityScore( scheduledCharge.getPriorityScore() );
        scheduledChargeDto.status( scheduledCharge.getStatus() );
        scheduledChargeDto.vehicleName( scheduledCharge.getVehicleName() );
        scheduledChargeDto.chargingStationName( scheduledCharge.getChargingStationName() );

        return scheduledChargeDto.build();
    }

    @Override
    public UpdateOccupationStatusRequest toUpdateOccupationStatusRequest(ScheduledCharge scheduledCharge) {
        if ( scheduledCharge == null ) {
            return null;
        }

        UpdateOccupationStatusRequest.UpdateOccupationStatusRequestBuilder updateOccupationStatusRequest = UpdateOccupationStatusRequest.builder();

        updateOccupationStatusRequest.chargeStartTime( scheduledCharge.getChargeStartTime() );
        updateOccupationStatusRequest.chargeEndTime( scheduledCharge.getChargeEndTime() );
        updateOccupationStatusRequest.chargingPower( scheduledCharge.getChargingPower() );
        updateOccupationStatusRequest.chargingStationId( scheduledCharge.getChargingStationId() );

        return updateOccupationStatusRequest.build();
    }
}
