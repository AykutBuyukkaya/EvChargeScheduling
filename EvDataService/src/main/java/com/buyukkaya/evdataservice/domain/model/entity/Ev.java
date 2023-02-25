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

    @Field("brand_id")
    private String brandId;

    @Field("vehicle_type")
    private String vehicleType;

    @Field("model")
    private String model;

    @Field("release_year")
    private String releaseYear;

    @Field("variant")
    private String variant;

    @Field("usable_battery_size")
    private Double usableBatterySize;

    @Field("average_consumption")
    private Double averageConsumption;

    @Field("ac_charger")
    private BasicDBObject acCharger;

    @Field("dc_charger")
    private BasicDBObject dcCharger;


}
