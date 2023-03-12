package com.buyukkaya.chargeschedulingservice.domain.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ElectricityPricingServiceRequest {

    @JsonProperty("startingDate")
    @JsonFormat(pattern = "yyyy.MM.dd HH.mm")
    @DateTimeFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime startingDate;

    @JsonProperty("endingDate")
    @JsonFormat(pattern = "yyyy.MM.dd HH.mm")
    @DateTimeFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime endingDate;

}
