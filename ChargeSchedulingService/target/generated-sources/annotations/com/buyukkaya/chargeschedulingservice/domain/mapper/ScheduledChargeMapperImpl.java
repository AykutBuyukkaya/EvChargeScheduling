package com.buyukkaya.chargeschedulingservice.domain.mapper;

import com.buyukkaya.chargeschedulingservice.domain.model.dto.ScheduledChargeDto;
import com.buyukkaya.chargeschedulingservice.domain.model.entity.ScheduledCharge;
import com.buyukkaya.chargeschedulingservice.domain.model.request.ChargeSchedulingRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-15T23:01:24+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class ScheduledChargeMapperImpl implements ScheduledChargeMapper {

    @Override
    public ScheduledCharge fromChargeSchedulingRequest(ChargeSchedulingRequest chargeSchedulingRequest) {
        if ( chargeSchedulingRequest == null ) {
            return null;
        }

        ScheduledCharge.ScheduledChargeBuilder scheduledCharge = ScheduledCharge.builder();

        scheduledCharge.evId( chargeSchedulingRequest.getEvId() );
        scheduledCharge.evTypeId( chargeSchedulingRequest.getEvTypeId() );
        scheduledCharge.chargingStationId( chargeSchedulingRequest.getChargingStationId() );
        scheduledCharge.arrivalTime( chargeSchedulingRequest.getArrivalTime() );
        scheduledCharge.departureTime( chargeSchedulingRequest.getDepartureTime() );
        scheduledCharge.currentSoC( chargeSchedulingRequest.getCurrentSoC() );
        scheduledCharge.expectedSoC( chargeSchedulingRequest.getExpectedSoC() );

        return scheduledCharge.build();
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

        return scheduledChargeDto.build();
    }
}
