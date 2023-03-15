package com.buyukkaya.chargeschedulingservice.domain.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChargeSchedulingRequest {

    @JsonProperty("evId")
    private UUID evId;

    @JsonProperty("evTypeId")
    private UUID evTypeId;

    @JsonProperty("chargingStationId")
    private String chargingStationId;

    @JsonFormat(pattern ="yyyy.MM.dd HH.mm")
    @DateTimeFormat(pattern = "yyyy.MM.dd HH.mm")
    @JsonProperty("arrivalTime")
    private LocalDateTime arrivalTime;

    @JsonFormat(pattern ="yyyy.MM.dd HH.mm")
    @DateTimeFormat(pattern = "yyyy.MM.dd HH.mm")
    @JsonProperty("departureTime")
    private LocalDateTime departureTime;

    @JsonProperty("currentSoC")
    private Double currentSoC;

    @JsonProperty("expectedSoC")
    private Double expectedSoC;


}
