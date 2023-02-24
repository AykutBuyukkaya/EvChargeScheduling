package com.buyukkaya.evdataservice.domain.model.response;

import com.buyukkaya.evdataservice.domain.model.dto.EvDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GetEvResponse extends BaseResponse {

    private EvDto ev;

    private List<EvDto> evList;

}
