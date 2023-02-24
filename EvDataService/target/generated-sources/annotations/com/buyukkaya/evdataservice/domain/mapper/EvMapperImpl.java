package com.buyukkaya.evdataservice.domain.mapper;

import com.buyukkaya.evdataservice.domain.model.dto.EvDto;
import com.buyukkaya.evdataservice.domain.model.entity.Ev;
import com.mongodb.BasicDBObject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-24T22:31:33+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class EvMapperImpl implements EvMapper {

    @Override
    public EvDto toDto(Ev ev) {
        if ( ev == null ) {
            return null;
        }

        EvDto.EvDtoBuilder evDto = EvDto.builder();

        if ( ev.getId() != null ) {
            evDto.id( UUID.fromString( ev.getId() ) );
        }
        evDto.vehicleType( ev.getVehicleType() );
        evDto.model( ev.getModel() );
        evDto.releaseYear( ev.getReleaseYear() );
        evDto.variant( ev.getVariant() );
        evDto.usableBatterySize( ev.getUsableBatterySize() );
        evDto.averageConsumption( ev.getAverageConsumption() );
        BasicDBObject basicDBObject = ev.getAcCharger();
        if ( basicDBObject != null ) {
            evDto.acCharger( new BasicDBObject( basicDBObject ) );
        }
        BasicDBObject basicDBObject1 = ev.getDcCharger();
        if ( basicDBObject1 != null ) {
            evDto.dcCharger( new BasicDBObject( basicDBObject1 ) );
        }

        return evDto.build();
    }

    @Override
    public List<EvDto> toDtoList(List<Ev> evList) {
        if ( evList == null ) {
            return null;
        }

        List<EvDto> list = new ArrayList<EvDto>( evList.size() );
        for ( Ev ev : evList ) {
            list.add( toDto( ev ) );
        }

        return list;
    }
}
