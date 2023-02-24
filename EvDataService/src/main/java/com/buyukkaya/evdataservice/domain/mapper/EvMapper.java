package com.buyukkaya.evdataservice.domain.mapper;

import com.buyukkaya.evdataservice.domain.model.dto.EvDto;
import com.buyukkaya.evdataservice.domain.model.entity.Ev;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EvMapper {

    EvMapper MAPPER = Mappers.getMapper(EvMapper.class);

    EvDto toDto(Ev ev);

    List<EvDto> toDtoList(List<Ev> evList);


}
