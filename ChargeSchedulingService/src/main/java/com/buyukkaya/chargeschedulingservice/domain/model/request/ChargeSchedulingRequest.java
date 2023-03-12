package com.buyukkaya.chargeschedulingservice.domain.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private UUID evId;

    private UUID evTypeId;

    private String chargingStationId;

    @JsonFormat(pattern ="yyyy.MM.dd HH.mm")
    @DateTimeFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime arrivalTime;

    @JsonFormat(pattern ="yyyy.MM.dd HH.mm")
    @DateTimeFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime departureTime;

    private Integer currentSoC;

    private Integer expectedSoC;


}
