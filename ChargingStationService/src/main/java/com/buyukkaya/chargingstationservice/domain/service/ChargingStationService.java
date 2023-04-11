package com.buyukkaya.chargingstationservice.domain.service;

import com.buyukkaya.chargingstationservice.domain.model.dto.ChargingStationDto;
import com.buyukkaya.chargingstationservice.domain.model.request.UpdateOccupationStatusRequest;

import java.util.List;

public interface ChargingStationService {

    ChargingStationDto getChargingStationById(String id);
    List<ChargingStationDto> getChargingStationList();

    void updateOccupationStatus(UpdateOccupationStatusRequest updateOccupationStatusRequest);

    void resetPowerUsage();

}
