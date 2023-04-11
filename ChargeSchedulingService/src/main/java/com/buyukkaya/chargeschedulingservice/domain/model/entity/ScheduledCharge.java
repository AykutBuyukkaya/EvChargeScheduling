package com.buyukkaya.chargeschedulingservice.domain.model.entity;

import com.buyukkaya.chargeschedulingservice.domain.model.enums.ScheduledChargeStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ScheduledCharge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID evId;

    private UUID evTypeId;

    private String chargingStationId;

    private LocalTime arrivalTime;

    private LocalTime departureTime;

    private Double currentSoC;

    private Double expectedSoC;

    private Double chargingPower;

    private Long requiredTimeToChargeInMinutes;

    private LocalTime chargeStartTime;

    private LocalTime chargeEndTime;

    private Double chargingCost;

    private Double priorityScore;

    private ScheduledChargeStatus status;

    private String vehicleName;

    private String chargingStationName;


}
