package com.buyukkaya.chargeschedulingservice.domain.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChargeSchedulingRequest {


    @JsonProperty("evTypeId")
    private UUID evTypeId;

    @JsonProperty("chargingStationId")
    private String chargingStationId;

    @JsonFormat(pattern ="HH:mm")
    @DateTimeFormat(pattern = "HH:mm")
    @JsonProperty("arrivalTime")
    private LocalTime arrivalTime;

    @JsonFormat(pattern ="HH:mm")
    @DateTimeFormat(pattern = "HH:mm")
    @JsonProperty("departureTime")
    private LocalTime departureTime;

    @JsonProperty("currentSoC")
    private Double currentSoC;

    @JsonProperty("expectedSoC")
    private Double expectedSoC;

    @JsonIgnore
    private Double priorityScore;

    @JsonProperty("vehicleName")
    private String vehicleName;

    @JsonProperty("chargingStationName")
    private String chargingStationName;



}
