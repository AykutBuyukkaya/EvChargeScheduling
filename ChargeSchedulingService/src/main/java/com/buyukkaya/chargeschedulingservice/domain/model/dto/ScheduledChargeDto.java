package com.buyukkaya.chargeschedulingservice.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
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

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "UTC")
    private LocalDateTime arrivalTime;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "UTC")
    private LocalDateTime departureTime;

    private Double currentSoC;

    private Double expectedSoC;

    private Double chargingPower;

    private Long requiredTimeToChargeInMinutes;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "UTC")
    private LocalDateTime chargeStartTime;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "UTC")
    private LocalDateTime chargeEndTime;

    private Double chargingCost;

}
