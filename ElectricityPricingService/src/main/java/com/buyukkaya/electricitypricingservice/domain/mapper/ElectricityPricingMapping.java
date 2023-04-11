package com.buyukkaya.electricitypricingservice.domain.mapper;

import com.buyukkaya.electricitypricingservice.domain.model.dto.ElectricityPricingDataDto;
import com.buyukkaya.electricitypricingservice.domain.model.dto.ExternalElectricityPricingDataDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Mapper(imports = {LocalDateTime.class, ZoneId.class, Instant.class})
public interface ElectricityPricingMapping {

    @Mapping(target = "date", expression = "java(LocalDateTime.ofInstant(Instant.ofEpochMilli(externalElectricityPricingDataDto.getMillisUTC()), ZoneId.of(\"EST\", ZoneId.SHORT_IDS)).plusDays(1))")
    ElectricityPricingDataDto fromExternal(ExternalElectricityPricingDataDto externalElectricityPricingDataDto);

    List<ElectricityPricingDataDto> fromExternalList(List<ExternalElectricityPricingDataDto> externalElectricityPricingDataDtoList);

}
