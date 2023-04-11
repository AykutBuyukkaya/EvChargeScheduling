package com.buyukkaya.chargingstationservice.domain.repository;

import com.buyukkaya.chargingstationservice.domain.model.entity.Transformer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TransformerRepository extends MongoRepository<Transformer, String> {

    @Override
    Optional<Transformer> findById(String s);
}
