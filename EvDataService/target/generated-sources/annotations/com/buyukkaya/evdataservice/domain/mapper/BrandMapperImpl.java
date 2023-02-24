package com.buyukkaya.evdataservice.domain.mapper;

import com.buyukkaya.evdataservice.domain.model.dto.BrandDto;
import com.buyukkaya.evdataservice.domain.model.entity.Brand;
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
public class BrandMapperImpl implements BrandMapper {

    @Override
    public BrandDto toDto(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        BrandDto.BrandDtoBuilder brandDto = BrandDto.builder();

        if ( brand.getId() != null ) {
            brandDto.id( UUID.fromString( brand.getId() ) );
        }
        brandDto.name( brand.getName() );

        return brandDto.build();
    }

    @Override
    public List<BrandDto> toDtoList(List<Brand> brandList) {
        if ( brandList == null ) {
            return null;
        }

        List<BrandDto> list = new ArrayList<BrandDto>( brandList.size() );
        for ( Brand brand : brandList ) {
            list.add( toDto( brand ) );
        }

        return list;
    }
}
