package com.buyukkaya.chargeschedulingservice.domain.model.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ChargingStationDto {

    private String id;

    private List<String> availablePowerOutputs;

}
