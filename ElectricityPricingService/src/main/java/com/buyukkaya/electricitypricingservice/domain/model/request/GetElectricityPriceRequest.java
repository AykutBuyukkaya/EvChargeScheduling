package com.buyukkaya.electricitypricingservice.domain.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GetElectricityPriceRequest {

    @JsonProperty("startingDate")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern ="yyyy.MM.dd HH.mm")
    @DateTimeFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime startingDate;

    @JsonProperty("endingDate")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern ="yyyy.MM.dd HH.mm")
    @DateTimeFormat(pattern = "yyyy.MM.dd HH.mm")
    private LocalDateTime endingDate;

}
