package com.buyukkaya.chargeschedulingservice.domain.util;

import com.buyukkaya.chargeschedulingservice.domain.exception.ChargeSchedulingException;
import com.buyukkaya.chargeschedulingservice.domain.model.entity.ScheduledCharge;
import com.buyukkaya.chargeschedulingservice.domain.model.response.DataCollectorResponse;

import java.time.temporal.ChronoUnit;

public class CommonFunctions {

    public static void calculateRequiredTimeToCharge(DataCollectorResponse dataCollectorResponse, ScheduledCharge scheduledCharge) {

        final Double batteryCapacity = dataCollectorResponse.getEvDataServiceResponse().getEv().getUsableBatterySize();
        final Double requiredChargingPercentage = (scheduledCharge.getExpectedSoC() - scheduledCharge.getCurrentSoC()) / 100;

        final Double requiredChargeInkWH = requiredChargingPercentage * batteryCapacity;
        final long requiredTimeToChargeInMinutes = Math.round(60 * (requiredChargeInkWH / scheduledCharge.getChargingPower()));

        if (requiredTimeToChargeInMinutes < ChronoUnit.MINUTES.between(scheduledCharge.getDepartureTime(), scheduledCharge.getArrivalTime())) {
            throw new ChargeSchedulingException("Could not access expected SoC in given time period. Please change expected SoC.");
        }
        scheduledCharge.setRequiredTimeToChargeInMinutes(requiredTimeToChargeInMinutes);
    }


}
