package com.buyukkaya.chargeschedulingservice.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ElectricityPriceDto {

    @JsonProperty("price")
    private Double price;

    @JsonProperty("date")
    @JsonFormat(pattern = "HH:mm", timezone = "UTC")
    private LocalTime date;

}
