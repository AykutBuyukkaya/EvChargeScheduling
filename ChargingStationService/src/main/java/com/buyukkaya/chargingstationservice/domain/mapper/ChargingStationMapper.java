package com.buyukkaya.chargingstationservice.domain.mapper;

import com.buyukkaya.chargingstationservice.domain.model.dto.ChargingStationDto;
import com.buyukkaya.chargingstationservice.domain.model.entity.ChargingStation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ChargingStationMapper {

    ChargingStationDto toDto(ChargingStation chargingStation);

    List<ChargingStationDto> toDtoList(List<ChargingStation> chargingStationList);

}
