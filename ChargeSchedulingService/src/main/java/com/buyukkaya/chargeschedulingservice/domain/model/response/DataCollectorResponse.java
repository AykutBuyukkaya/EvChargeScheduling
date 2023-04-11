package com.buyukkaya.chargeschedulingservice.domain.model.response;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DataCollectorResponse {

    private UUID userEvId;

    private UUID evTypeId;

    private String chargingStationId;

    private LocalTime arrivalTime;

    private LocalTime departureTime;

    private EvDataServiceResponse evDataServiceResponse;

    private ChargingStationServiceResponse chargingStationServiceResponse;

    private ElectricityPricingServiceResponse electricityPricingServiceResponse;

}
