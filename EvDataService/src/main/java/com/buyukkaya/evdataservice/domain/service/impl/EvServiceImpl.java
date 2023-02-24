package com.buyukkaya.evdataservice.domain.service.impl;

import com.buyukkaya.evdataservice.domain.mapper.EvMapper;
import com.buyukkaya.evdataservice.domain.model.dto.EvDto;
import com.buyukkaya.evdataservice.domain.model.exception.NoDataFoundException;
import com.buyukkaya.evdataservice.domain.repository.EvRepository;
import com.buyukkaya.evdataservice.domain.service.EvService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvServiceImpl implements EvService {

    private final EvRepository evRepository;
    private final EvMapper evMapper;

    public EvServiceImpl(EvRepository evRepository, EvMapper evMapper) {
        this.evRepository = evRepository;
        this.evMapper = evMapper;
    }

    @Override
    public List<EvDto> findAllByBrandId(String brandId) {
        return evMapper.toDtoList(evRepository.findAllByBrandId(brandId));
    }

    @Override
    public EvDto findById(String id) {
        return evMapper.toDto(evRepository.findById(id).orElseThrow(NoDataFoundException::new));
    }
}
