package com.buyukkaya.chargingstationservice.domain.mapper;

import com.buyukkaya.chargingstationservice.domain.model.dto.ChargingStationDto;
import com.buyukkaya.chargingstationservice.domain.model.entity.ChargingStation;
import com.buyukkaya.chargingstationservice.domain.model.entity.Transformer;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-10T22:06:44+0300",
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

        Map<LocalTime, Double> powerUsageLimit = chargingStationTransformerPowerUsageLimit( chargingStation );
        Map<LocalTime, Double> map = powerUsageLimit;
        if ( map != null ) {
            chargingStationDto.powerUsageLimit( new LinkedHashMap<LocalTime, Double>( map ) );
        }
        Map<LocalTime, Double> powerUsage = chargingStationTransformerPowerUsage( chargingStation );
        Map<LocalTime, Double> map1 = powerUsage;
        if ( map1 != null ) {
            chargingStationDto.powerUsage( new LinkedHashMap<LocalTime, Double>( map1 ) );
        }
        chargingStationDto.id( chargingStation.getId() );
        chargingStationDto.name( chargingStation.getName() );
        List<String> list = chargingStation.getAvailablePowerOutputs();
        if ( list != null ) {
            chargingStationDto.availablePowerOutputs( new ArrayList<String>( list ) );
        }
        Map<LocalTime, Double> map2 = chargingStation.getOccupationMap();
        if ( map2 != null ) {
            chargingStationDto.occupationMap( new LinkedHashMap<LocalTime, Double>( map2 ) );
        }

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

    private Map<LocalTime, Double> chargingStationTransformerPowerUsageLimit(ChargingStation chargingStation) {
        if ( chargingStation == null ) {
            return null;
        }
        Transformer transformer = chargingStation.getTransformer();
        if ( transformer == null ) {
            return null;
        }
        Map<LocalTime, Double> powerUsageLimit = transformer.getPowerUsageLimit();
        if ( powerUsageLimit == null ) {
            return null;
        }
        return powerUsageLimit;
    }

    private Map<LocalTime, Double> chargingStationTransformerPowerUsage(ChargingStation chargingStation) {
        if ( chargingStation == null ) {
            return null;
        }
        Transformer transformer = chargingStation.getTransformer();
        if ( transformer == null ) {
            return null;
        }
        Map<LocalTime, Double> powerUsage = transformer.getPowerUsage();
        if ( powerUsage == null ) {
            return null;
        }
        return powerUsage;
    }
}
