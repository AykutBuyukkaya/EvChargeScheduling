package com.buyukkaya.chargeschedulingservice.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ElectricityPriceDto {

    @JsonProperty("price")
    private Double price;

    @JsonProperty("date")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "UTC")
    private Instant date;

}