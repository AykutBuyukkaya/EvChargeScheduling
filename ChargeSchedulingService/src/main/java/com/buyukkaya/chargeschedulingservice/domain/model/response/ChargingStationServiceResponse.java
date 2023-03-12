package com.buyukkaya.chargeschedulingservice.domain.model.response;

import com.buyukkaya.chargeschedulingservice.domain.model.dto.ChargingStationDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ChargingStationServiceResponse {

    private ChargingStationDto chargingStation;

}
