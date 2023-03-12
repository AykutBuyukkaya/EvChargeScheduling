package com.buyukkaya.chargingstationservice.domain.mapper;

import com.buyukkaya.chargingstationservice.domain.model.dto.ChargingStationDto;
import com.buyukkaya.chargingstationservice.domain.model.entity.ChargingStation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-09T11:30:00+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class ChargingStationMapperImpl implements ChargingStationMapper {

    @Override
    public ChargingStationDto toDto(ChargingStation chargingStation) {
        if ( chargingStation == null ) {
            return null;
        }

        ChargingStationDto.ChargingStationDtoBuilder chargingStationDto = ChargingStationDto.builder();

        chargingStationDto.id( chargingStation.getId() );
        List<String> list = chargingStation.getAvailablePowerOutputs();
        if ( list != null ) {
            chargingStationDto.availablePowerOutputs( new ArrayList<String>( list ) );
        }
        chargingStationDto.chargerType( chargingStation.getChargerType() );

        return chargingStationDto.build();
    }

    @Override
    public List<ChargingStationDto> toDtoList(List<ChargingStation> chargingStationList) {
        if ( chargingStationList == null ) {
            return null;
        }

        List<ChargingStationDto> list = new ArrayList<ChargingStationDto>( chargingStationList.size() );
        for ( ChargingStation chargingStation : chargingStationList ) {
            list.add( toDto( chargingStation ) );
        }

        return list;
    }
}
