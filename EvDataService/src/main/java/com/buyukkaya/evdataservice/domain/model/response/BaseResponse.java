package com.buyukkaya.evdataservice.domain.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseResponse {

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "UTC")
    private Instant timestamp;


}
