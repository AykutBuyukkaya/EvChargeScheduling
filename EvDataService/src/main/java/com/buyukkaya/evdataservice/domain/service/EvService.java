package com.buyukkaya.evdataservice.domain.service;

import com.buyukkaya.evdataservice.domain.model.dto.EvDto;

import java.util.List;
import java.util.UUID;

public interface EvService {

    List<EvDto> findAllByBrandId(String brandId);

    EvDto findById(String id);

}
