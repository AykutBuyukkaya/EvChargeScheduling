package com.buyukkaya.chargingstationservice.domain.controller;

import com.buyukkaya.chargingstationservice.domain.model.response.GetChargingStationResponse;
import com.buyukkaya.chargingstationservice.domain.service.ChargingStationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class ChargingStationController {

    private final ChargingStationService chargingStationService;

    public ChargingStationController(ChargingStationService chargingStationService) {
        this.chargingStationService = chargingStationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetChargingStationResponse> getChargingStationById(@PathVariable String id) {
        return ResponseEntity.ok(GetChargingStationResponse.builder()
                .chargingStation(chargingStationService.getChargingStationById(id))
                .message("Charging station got successfully!")
                .timestamp(Instant.now())
                .build());
    }

    @GetMapping("/all")
    public ResponseEntity<GetChargingStationResponse> getAllChargingStationData() {
        return ResponseEntity.ok(GetChargingStationResponse.builder()
                .chargingStationList(chargingStationService.getChargingStationList())
                .message("Charging station got successfully!")
                .timestamp(Instant.now())
                .build());
    }

}
