package com.buyukkaya.chargingstationservice.domain.model.response;

import com.buyukkaya.chargingstationservice.domain.model.dto.ChargingStationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class GetChargingStationResponse extends BaseResponse{

    private ChargingStationDto chargingStation;

    private List<ChargingStationDto> chargingStationList;

}
