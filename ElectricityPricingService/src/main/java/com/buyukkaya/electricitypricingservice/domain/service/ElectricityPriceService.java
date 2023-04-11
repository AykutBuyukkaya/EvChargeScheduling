package com.buyukkaya.electricitypricingservice.domain.service;

import com.buyukkaya.electricitypricingservice.domain.model.dto.ElectricityPricingDataDto;
import com.buyukkaya.electricitypricingservice.domain.model.request.GetElectricityPriceRequest;

import java.util.List;

public interface ElectricityPriceService {

    List<ElectricityPricingDataDto> getElectricityPricingData(GetElectricityPriceRequest request);

}
