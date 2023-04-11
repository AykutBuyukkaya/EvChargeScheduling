package com.buyukkaya.chargeschedulingservice.domain.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ElectricityPricingServiceRequest {

    @JsonProperty("startingDate")
    @JsonFormat(pattern = "HH.mm")
    @DateTimeFormat(pattern = "HH.mm")
    private LocalTime startingDate;

    @JsonProperty("endingDate")
    @JsonFormat(pattern = "HH.mm")
    @DateTimeFormat(pattern = "HH.mm")
    private LocalTime endingDate;

}
