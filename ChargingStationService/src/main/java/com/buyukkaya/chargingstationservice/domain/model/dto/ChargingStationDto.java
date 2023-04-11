package com.buyukkaya.chargingstationservice.domain.model.dto;

import lombok.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ChargingStationDto {

    private String id;
    private String name;
    private List<String> availablePowerOutputs;
    private Map<LocalTime, Double> powerUsageLimit;
    private Map<LocalTime, Double> powerUsage;
    private Map<LocalTime, Double> occupationMap;

}
