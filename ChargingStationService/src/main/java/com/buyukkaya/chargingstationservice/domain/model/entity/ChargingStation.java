package com.buyukkaya.chargingstationservice.domain.model.entity;

import com.buyukkaya.chargingstationservice.domain.model.enums.ChargerType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Document("charging_stations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChargingStation {

    @Id
    private String id;

    @Field("available_power_outputs")
    private List<String> availablePowerOutputs;

    @Field("name")
    private String name;
    @Field("charger_type")
    @Enumerated(EnumType.STRING)
    private ChargerType chargerType;

    @DBRef
    private Transformer transformer;

    @Field("occupation_map")
    private Map<LocalTime, Double> occupationMap;



}
