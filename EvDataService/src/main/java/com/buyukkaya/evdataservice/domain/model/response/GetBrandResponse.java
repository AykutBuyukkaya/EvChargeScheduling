package com.buyukkaya.evdataservice.domain.model.response;

import com.buyukkaya.evdataservice.domain.model.dto.BrandDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GetBrandResponse extends BaseResponse {
    private List<BrandDto> brandList;

}
