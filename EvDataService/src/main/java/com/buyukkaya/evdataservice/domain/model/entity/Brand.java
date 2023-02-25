package com.buyukkaya.evdataservice.domain.model.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.io.Serializable;


@Document("brands")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Brand implements Serializable {

    @Id
    private String id;

    @Field("name")
    private String name;

}
