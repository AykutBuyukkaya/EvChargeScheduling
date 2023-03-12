package com.buyukkaya.chargingstationservice.domain.service.impl;

import com.buyukkaya.chargingstationservice.domain.exception.ChargingStationNotFoundException;
import com.buyukkaya.chargingstationservice.domain.mapper.ChargingStationMapper;
import com.buyukkaya.chargingstationservice.domain.model.dto.ChargingStationDto;
import com.buyukkaya.chargingstationservice.domain.repository.ChargingStationRepository;
import com.buyukkaya.chargingstationservice.domain.service.ChargingStationService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class DefaultChargingStationService implements ChargingStationService {

    private final ChargingStationRepository chargingStationRepository;
    private final ChargingStationMapper chargingStationMapper;

    public DefaultChargingStationService(ChargingStationRepository chargingStationRepository, ChargingStationMapper chargingStationMapper) {
        this.chargingStationRepository = chargingStationRepository;
        this.chargingStationMapper = chargingStationMapper;
    }

    @Override
    public ChargingStationDto getChargingStationById(String id) {
        return chargingStationMapper.toDto(chargingStationRepository.findById(id).orElseThrow(ChargingStationNotFoundException::new));
    }

    @Override
    public List<ChargingStationDto> getChargingStationList() {
        return chargingStationMapper.toDtoList(chargingStationRepository.findAll());
    }
}
