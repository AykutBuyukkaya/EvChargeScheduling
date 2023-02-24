package com.buyukkaya.evdataservice.domain.model.dto;

import com.buyukkaya.evdataservice.domain.model.entity.VehicleType;
import com.mongodb.BasicDBObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EvDto {

    private UUID id;

    private BrandDto brand;

    private VehicleType vehicleType;

    private String model;

    private String releaseYear;

    private String variant;

    private Double usableBatterySize;

    private Double averageConsumption;

    private BasicDBObject acCharger;

    private BasicDBObject dcCharger;

}
