package com.buyukkaya.chargeschedulingservice.domain.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
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

    private LocalDateTime arrivalTime;

    private LocalDateTime departureTime;

    private Integer currentSoC;

    private Integer expectedSoC;

    private Double chargingPower;

    private Double requiredTimeToChargeInHours;

}
