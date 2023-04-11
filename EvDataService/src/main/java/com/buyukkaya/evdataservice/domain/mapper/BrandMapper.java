package com.buyukkaya.evdataservice.domain.mapper;

import com.buyukkaya.evdataservice.domain.model.dto.BrandDto;
import com.buyukkaya.evdataservice.domain.model.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BrandMapper {

    BrandMapper MAPPER = Mappers.getMapper(BrandMapper.class);

    BrandDto toDto(Brand brand);

    List<BrandDto> toDtoList(List<Brand> brandList);

}
