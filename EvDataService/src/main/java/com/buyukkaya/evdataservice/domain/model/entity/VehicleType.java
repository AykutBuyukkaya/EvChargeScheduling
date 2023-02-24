package com.buyukkaya.evdataservice.domain.model.entity;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Getter
public enum VehicleType {

    CAR("CAR"),
    MOTORBIKE("MOTORBIKE");

    private final String description;

    VehicleType(String description) {
        this.description = description;
    }

    public static VehicleType getByDescription(String description) {
        Optional<VehicleType> vehicleType = Arrays.stream(VehicleType.values()).filter(s -> s.getDescription().equalsIgnoreCase(description))
                .findFirst();

        if (vehicleType.isPresent()) {
            return vehicleType.get();
        } else {
            log.warn("Cannot find vehicle type!");
            return null;
        }

    }

}
