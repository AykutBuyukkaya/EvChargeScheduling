package com.buyukkaya.evdataservice.domain.service;

import com.buyukkaya.evdataservice.domain.model.dto.EvDto;

import java.util.List;

public interface EvService {

    List<EvDto> findAllByBrandId(String brandId);

    EvDto findById(String id);

}
