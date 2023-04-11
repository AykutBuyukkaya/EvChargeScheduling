package com.buyukkaya.chargingstationservice.domain.service.impl;

import com.buyukkaya.chargingstationservice.domain.exception.ChargingStationNotFoundException;
import com.buyukkaya.chargingstationservice.domain.mapper.ChargingStationMapper;
import com.buyukkaya.chargingstationservice.domain.model.dto.ChargingStationDto;
import com.buyukkaya.chargingstationservice.domain.model.entity.ChargingStation;
import com.buyukkaya.chargingstationservice.domain.model.entity.Transformer;
import com.buyukkaya.chargingstationservice.domain.model.request.UpdateOccupationStatusRequest;
import com.buyukkaya.chargingstationservice.domain.repository.ChargingStationRepository;
import com.buyukkaya.chargingstationservice.domain.repository.TransformerRepository;
import com.buyukkaya.chargingstationservice.domain.service.ChargingStationService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Primary
@Service
public class DefaultChargingStationService implements ChargingStationService {

    private final ChargingStationRepository chargingStationRepository;
    private final TransformerRepository transformerRepository;
    private final ChargingStationMapper chargingStationMapper;

    public DefaultChargingStationService(ChargingStationRepository chargingStationRepository, TransformerRepository transformerRepository, ChargingStationMapper chargingStationMapper) {
        this.chargingStationRepository = chargingStationRepository;
        this.transformerRepository = transformerRepository;
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

    @Override
    public void updateOccupationStatus(UpdateOccupationStatusRequest updateOccupationStatusRequest) {
        ChargingStation chargingStation = chargingStationRepository.findById(updateOccupationStatusRequest.getChargingStationId())
                .orElseThrow(ChargingStationNotFoundException::new);
        Transformer transformer = chargingStation.getTransformer();

        for (LocalTime ii = updateOccupationStatusRequest.getChargeStartTime(); ii.isBefore(updateOccupationStatusRequest.getChargeEndTime()); ii = ii.plusMinutes(1)) {
            chargingStation.getOccupationMap().put(ii, updateOccupationStatusRequest.getChargingPower());
            transformer.getPowerUsage().put(ii, transformer.getPowerUsage().get(ii) + updateOccupationStatusRequest.getChargingPower());
        }

        transformerRepository.save(transformer);
        chargingStationRepository.save(chargingStation);


    }

    @Override
    public void resetPowerUsage() {
       List<ChargingStation> chargingStationList = chargingStationRepository.findAll();
       Transformer transformer = chargingStationList.get(0).getTransformer();

       Map<LocalTime, Double> powerUsage = new TreeMap<>();

        for (LocalTime i = LocalTime.MIN.plusMinutes(1); i.isBefore(LocalTime.of(23, 59, 0)); i = i.plusMinutes(1)) {

            powerUsage.put(i, 0.0);

        }

       transformer.setPowerUsage(powerUsage);
       chargingStationList.forEach(chargingStation -> chargingStation.setOccupationMap(Collections.emptyMap()));

       transformerRepository.save(transformer);
       chargingStationRepository.saveAll(chargingStationList);

    }
}
