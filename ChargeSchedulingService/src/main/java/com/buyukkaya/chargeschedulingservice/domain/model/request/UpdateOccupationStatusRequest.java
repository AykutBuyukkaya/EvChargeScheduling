package com.buyukkaya.chargeschedulingservice.domain.model.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class UpdateOccupationStatusRequest {

    private LocalTime chargeStartTime;
    private LocalTime chargeEndTime;
    private Double chargingPower;
    private String chargingStationId;

}
