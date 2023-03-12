package com.buyukkaya.chargingstationservice.domain.repository;

import com.buyukkaya.chargingstationservice.domain.model.entity.ChargingStation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface ChargingStationRepository extends MongoRepository<ChargingStation, UUID> {

    Optional<ChargingStation> findById(String id);

}
