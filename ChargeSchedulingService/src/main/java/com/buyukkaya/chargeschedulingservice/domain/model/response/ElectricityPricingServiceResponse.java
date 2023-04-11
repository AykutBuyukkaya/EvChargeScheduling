package com.buyukkaya.chargeschedulingservice.domain.model.response;

import com.buyukkaya.chargeschedulingservice.domain.model.dto.ElectricityPriceDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ElectricityPricingServiceResponse {

    private List<ElectricityPriceDto> electricityPricingDataList;

}
