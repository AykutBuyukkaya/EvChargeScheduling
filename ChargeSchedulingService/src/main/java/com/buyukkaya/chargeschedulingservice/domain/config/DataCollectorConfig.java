package com.buyukkaya.chargeschedulingservice.domain.config;

import com.buyukkaya.chargeschedulingservice.domain.service.DataCollectorService;
import com.buyukkaya.chargeschedulingservice.domain.service.impl.ChargingStationDataCollectorService;
import com.buyukkaya.chargeschedulingservice.domain.service.impl.ElectricityPricingDataCollectorService;
import com.buyukkaya.chargeschedulingservice.domain.service.impl.EvDataCollectorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataCollectorConfig {


    @Bean
    @Primary
    public DataCollectorService defaultDataCollectorChain(EvDataCollectorService evDataCollectorService, ChargingStationDataCollectorService chargingStationDataCollectorService, ElectricityPricingDataCollectorService electricityPricingDataCollectorService) {

        evDataCollectorService.setNextService(chargingStationDataCollectorService);
        chargingStationDataCollectorService.setNextService(electricityPricingDataCollectorService);

        return evDataCollectorService;

    }

}
