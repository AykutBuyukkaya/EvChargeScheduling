package com.buyukkaya.electricitypricingservice.domain.controller;

import com.buyukkaya.electricitypricingservice.domain.model.request.GetElectricityPriceRequest;
import com.buyukkaya.electricitypricingservice.domain.model.response.ElectricityPricingDataResponse;
import com.buyukkaya.electricitypricingservice.domain.service.ElectricityPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@Slf4j
@RestController
public class ElectricityDataController {


    private final ElectricityPriceService fiveMinuteElectricityPricingService;
    private final ElectricityPriceService oneMinuteElectricityPricingService;

    public ElectricityDataController(ElectricityPriceService fiveMinuteElectricityPricingService, @Qualifier("one-minute-pricing") ElectricityPriceService oneMinuteElectricityPricingService) {
        this.fiveMinuteElectricityPricingService = fiveMinuteElectricityPricingService;
        this.oneMinuteElectricityPricingService = oneMinuteElectricityPricingService;
    }

    @PostMapping("/between-dates")
    public ResponseEntity<ElectricityPricingDataResponse> getElectricityData(@RequestBody GetElectricityPriceRequest request) {
        return ResponseEntity.ok(ElectricityPricingDataResponse
                .builder()
                .electricityPricingDataList(fiveMinuteElectricityPricingService.getElectricityPricingData(request))
                .timestamp(Instant.now())
                .message("Electricity price data got successfully!")
                .build());
    }

    @PostMapping("/between-dates/one-minute-data")
    public ResponseEntity<ElectricityPricingDataResponse> getOneMinuteElectricityPricingData(@RequestBody GetElectricityPriceRequest request) {
        return ResponseEntity.ok(ElectricityPricingDataResponse
                .builder()
                .electricityPricingDataList(oneMinuteElectricityPricingService.getElectricityPricingData(request))
                .timestamp(Instant.now())
                .message("Electricity price data got successfully!")
                .build());
    }

}
