package com.buyukkaya.chargeschedulingservice.domain.model.dto;

import com.buyukkaya.chargeschedulingservice.domain.model.enums.ScheduledChargeStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ScheduledChargeDto {

    private UUID id;

    private UUID evId;

    private UUID evTypeId;

    private String chargingStationId;

    @JsonFormat(pattern = "HH:mm", timezone = "UTC")
    private LocalTime arrivalTime;

    @JsonFormat(pattern = "HH:mm", timezone = "UTC")
    private LocalTime departureTime;

    private Double currentSoC;

    private Double expectedSoC;

    private Double chargingPower;

    private Long requiredTimeToChargeInMinutes;

    @JsonFormat(pattern = "HH:mm", timezone = "UTC")
    private LocalTime chargeStartTime;

    @JsonFormat(pattern = "HH:mm", timezone = "UTC")
    private LocalTime chargeEndTime;

    private Double chargingCost;

    private Double priorityScore;

    private ScheduledChargeStatus status;

    private String vehicleName;

    private String chargingStationName;

}
