package com.buyukkaya.chargeschedulingservice.domain.model.dto;

import lombok.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ChargingStationDto {

    private String id;
    private List<String> availablePowerOutputs;
    private Map<LocalTime, Double> powerUsageLimit;
    private Map<LocalTime, Double> powerUsage;

}
