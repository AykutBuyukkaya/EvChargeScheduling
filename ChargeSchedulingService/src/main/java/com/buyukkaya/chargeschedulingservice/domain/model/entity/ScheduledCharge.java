package com.buyukkaya.chargeschedulingservice.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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

    private Double currentSoC;

    private Double expectedSoC;

    private Double chargingPower;

    private Long requiredTimeToChargeInMinutes;

    private LocalDateTime chargeStartTime;

    private LocalDateTime chargeEndTime;

    private Double chargingCost;


}
