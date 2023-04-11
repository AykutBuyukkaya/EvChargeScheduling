package com.buyukkaya.chargeschedulingservice.domain.config;

import com.buyukkaya.chargeschedulingservice.domain.service.collector.DataCollectorService;
import com.buyukkaya.chargeschedulingservice.domain.service.collector.ChargingStationDataCollectorService;
import com.buyukkaya.chargeschedulingservice.domain.service.collector.ElectricityPricingDataCollectorService;
import com.buyukkaya.chargeschedulingservice.domain.service.collector.EvDataCollectorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataCollectorConfig {

    @Bean("minimalPricingChain")
    public DataCollectorService defaultDataCollectorChain(EvDataCollectorService evDataCollectorService, ChargingStationDataCollectorService chargingStationDataCollectorService, ElectricityPricingDataCollectorService electricityPricingDataCollectorService) {

        evDataCollectorService.setNextService(chargingStationDataCollectorService);
        chargingStationDataCollectorService.setNextService(electricityPricingDataCollectorService);

        return evDataCollectorService;

    }

    @Bean("peakShavingChain")
    public DataCollectorService peakShavingDataCollectorChain(EvDataCollectorService evDataCollectorService, ChargingStationDataCollectorService chargingStationDataCollectorService) {

        evDataCollectorService.setNextService(chargingStationDataCollectorService);
        chargingStationDataCollectorService.setNextService(null);
        return evDataCollectorService;
    }


}
