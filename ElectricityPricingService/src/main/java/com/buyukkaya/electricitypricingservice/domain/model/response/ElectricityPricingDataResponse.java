package com.buyukkaya.electricitypricingservice.domain.model.response;

import com.buyukkaya.electricitypricingservice.domain.model.dto.ElectricityPricingDataDto;
import com.buyukkaya.electricitypricingservice.domain.model.dto.ExternalElectricityPricingDataDto;
import lombok.*;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ElectricityPricingDataResponse {

    private Instant timestamp;
    private String message;
    private List<ElectricityPricingDataDto> electricityPricingDataList;

}
