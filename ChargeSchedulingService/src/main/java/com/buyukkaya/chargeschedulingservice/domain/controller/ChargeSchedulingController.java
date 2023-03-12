package com.buyukkaya.chargeschedulingservice.domain.controller;

import com.buyukkaya.chargeschedulingservice.domain.model.request.ChargeSchedulingRequest;
import com.buyukkaya.chargeschedulingservice.domain.model.response.DataCollectorResponse;
import com.buyukkaya.chargeschedulingservice.domain.service.ChargeSchedulingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChargeSchedulingController {

    private final ChargeSchedulingService chargeSchedulingService;

    public ChargeSchedulingController(ChargeSchedulingService chargeSchedulingService) {
        this.chargeSchedulingService = chargeSchedulingService;
    }

    @PostMapping("/schedule-charge")
    public ResponseEntity<DataCollectorResponse> scheduleCharge(@RequestBody ChargeSchedulingRequest chargeSchedulingRequest) {
        return ResponseEntity.ok(chargeSchedulingService.scheduleCharge(chargeSchedulingRequest));
    }

}
