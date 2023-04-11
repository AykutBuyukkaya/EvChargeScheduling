package com.buyukkaya.chargeschedulingservice.domain.controller;

import com.buyukkaya.chargeschedulingservice.domain.model.request.ChargeSchedulingRequest;
import com.buyukkaya.chargeschedulingservice.domain.model.response.ChargeSchedulingResponse;
import com.buyukkaya.chargeschedulingservice.domain.service.ChargeSchedulingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ChargeSchedulingController {

    private final ChargeSchedulingService chargeSchedulingService;

    public ChargeSchedulingController(ChargeSchedulingService chargeSchedulingService) {
        this.chargeSchedulingService = chargeSchedulingService;
    }

    @PostMapping()
    public ResponseEntity<ChargeSchedulingResponse> scheduleCharge(@RequestBody ChargeSchedulingRequest chargeSchedulingRequest) {
        return ResponseEntity.ok(ChargeSchedulingResponse.builder()
                .scheduledCharge(chargeSchedulingService.scheduleCharge(chargeSchedulingRequest))
                .message("Charge scheduling successful.")
                .timestamp(Instant.now())
                .build());
    }

    @PostMapping("/peak-shaving")
    public ResponseEntity<ChargeSchedulingResponse> scheduleCharge(@RequestBody List<ChargeSchedulingRequest> chargeSchedulingRequestList) {
        return ResponseEntity.ok(ChargeSchedulingResponse.builder()
                .scheduledChargeList(chargeSchedulingService.scheduleChargePeakShaving(chargeSchedulingRequestList))
                .message("Charge scheduling successfully with peak shaving algorithms.")
                .timestamp(Instant.now())
                .build());
    }

}
