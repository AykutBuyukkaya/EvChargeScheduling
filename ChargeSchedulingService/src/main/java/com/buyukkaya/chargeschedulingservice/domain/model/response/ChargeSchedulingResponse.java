package com.buyukkaya.chargeschedulingservice.domain.model.response;

import com.buyukkaya.chargeschedulingservice.domain.model.dto.ScheduledChargeDto;
import lombok.*;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChargeSchedulingResponse {

    private ScheduledChargeDto scheduledCharge;

    private List<ScheduledChargeDto> scheduledChargeList;

    private Instant timestamp;

    private String message;

}
