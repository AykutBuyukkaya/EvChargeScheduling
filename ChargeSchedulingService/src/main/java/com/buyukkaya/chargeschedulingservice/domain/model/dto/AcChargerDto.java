package com.buyukkaya.chargeschedulingservice.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AcChargerDto {

    @JsonProperty("usable_phases")
    private Integer usablePhases;

    @JsonProperty("ports")
    private List<String> ports;

    @JsonProperty("max_power")
    private Double maxPower;

    @JsonProperty("power_per_charging_point")
    private Map<String, Double> powerPerChargingPoint;


}
