package com.buyukkaya.evdataservice.domain.repository;

import com.buyukkaya.evdataservice.domain.model.entity.Ev;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvRepository extends MongoRepository<Ev, String> {

    List<Ev> findAllByBrandId(String brandId);
    List<Ev> findAll();


}
