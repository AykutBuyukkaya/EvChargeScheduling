package com.buyukkaya.electricitypricingservice.domain.mapper;

import com.buyukkaya.electricitypricingservice.domain.model.dto.ElectricityPricingDataDto;
import com.buyukkaya.electricitypricingservice.domain.model.dto.ExternalElectricityPricingDataDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.util.List;

@Mapper(imports = {Instant.class})
public interface ElectricityPricingMapping {

    @Mapping(target = "date", expression = "java(Instant.ofEpochMilli(externalElectricityPricingDataDto.getMillisUTC()))")
    ElectricityPricingDataDto fromExternal(ExternalElectricityPricingDataDto externalElectricityPricingDataDto);

    List<ElectricityPricingDataDto> fromExternalList(List<ExternalElectricityPricingDataDto> externalElectricityPricingDataDtoList);

}
