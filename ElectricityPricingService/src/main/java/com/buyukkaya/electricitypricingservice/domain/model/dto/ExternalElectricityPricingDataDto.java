package com.buyukkaya.electricitypricingservice.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ExternalElectricityPricingDataDto {

    @JsonProperty("price")
    private String price;

    @JsonProperty("millisUTC")
    private Long millisUTC;


}
