package com.buyukkaya.chargingstationservice.domain.model.dto;

import com.buyukkaya.chargingstationservice.domain.model.enums.ChargerType;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ChargingStationDto {

    private String id;

    private List<String> availablePowerOutputs;

    private ChargerType chargerType;

}
