package com.buyukkaya.chargingstationservice.domain.repository;

import com.buyukkaya.chargingstationservice.domain.model.entity.ChargingStation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChargingStationRepository extends MongoRepository<ChargingStation, String> {

    Optional<ChargingStation> findById(String id);

}
