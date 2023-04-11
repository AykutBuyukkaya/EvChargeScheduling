package com.buyukkaya.evdataservice.domain.repository;

import com.buyukkaya.evdataservice.domain.model.entity.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BrandRepository extends MongoRepository<Brand, String> {

    List<Brand> findAll();

}
