package com.buyukkaya.electricitypricingservice.domain.mapper;

import com.buyukkaya.electricitypricingservice.domain.model.dto.ElectricityPricingDataDto;
import com.buyukkaya.electricitypricingservice.domain.model.dto.ExternalElectricityPricingDataDto;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-10T22:06:43+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class ElectricityPricingMappingImpl implements ElectricityPricingMapping {

    @Override
    public ElectricityPricingDataDto fromExternal(ExternalElectricityPricingDataDto externalElectricityPricingDataDto) {
        if ( externalElectricityPricingDataDto == null ) {
            return null;
        }

        ElectricityPricingDataDto.ElectricityPricingDataDtoBuilder electricityPricingDataDto = ElectricityPricingDataDto.builder();

        if ( externalElectricityPricingDataDto.getPrice() != null ) {
            electricityPricingDataDto.price( Double.parseDouble( externalElectricityPricingDataDto.getPrice() ) );
        }

        electricityPricingDataDto.date( LocalDateTime.ofInstant(Instant.ofEpochMilli(externalElectricityPricingDataDto.getMillisUTC()), ZoneId.of("EST", ZoneId.SHORT_IDS)).plusDays(1) );

        return electricityPricingDataDto.build();
    }

    @Override
    public List<ElectricityPricingDataDto> fromExternalList(List<ExternalElectricityPricingDataDto> externalElectricityPricingDataDtoList) {
        if ( externalElectricityPricingDataDtoList == null ) {
            return null;
        }

        List<ElectricityPricingDataDto> list = new ArrayList<ElectricityPricingDataDto>( externalElectricityPricingDataDtoList.size() );
        for ( ExternalElectricityPricingDataDto externalElectricityPricingDataDto : externalElectricityPricingDataDtoList ) {
            list.add( fromExternal( externalElectricityPricingDataDto ) );
        }

        return list;
    }
}
