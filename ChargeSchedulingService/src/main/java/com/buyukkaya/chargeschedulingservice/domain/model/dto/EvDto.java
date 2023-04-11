package com.buyukkaya.chargeschedulingservice.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class EvDto {

    @JsonProperty("model")
    private String model;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("releaseYear")
    private String releaseYear;

    @JsonProperty("variant")
    private String variant;

    @JsonProperty("usableBatterySize")
    private Double usableBatterySize;

    @JsonProperty("acCharger")
    private AcChargerDto acCharger;

}
