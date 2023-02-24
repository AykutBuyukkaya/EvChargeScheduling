package com.buyukkaya.evdataservice.domain.model.entity;

import com.mongodb.BasicDBObject;
import lombok.*;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.io.Serializable;

@Document("evs")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Ev implements Serializable {

    @Id
    private String id;

    @Field(name = "brand_id")
    private String brandId;

    private VehicleType vehicleType;

    private String model;

    private String releaseYear;

    private String variant;

    private Double usableBatterySize;

    private Double averageConsumption;

    private BasicDBObject acCharger;

    private BasicDBObject dcCharger;


}
