package com.buyukkaya.chargingstationservice.domain.model.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalTime;
import java.util.Map;

@Document("transformer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Transformer {

    @Id
    private String id;

    @Field("power_usage_limit")
    private Map<LocalTime, Double> powerUsageLimit;

    @Field("power_usage")
    private Map<LocalTime, Double> powerUsage;

}
