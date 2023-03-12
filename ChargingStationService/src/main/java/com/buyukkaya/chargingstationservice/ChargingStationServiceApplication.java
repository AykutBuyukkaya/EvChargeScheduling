package com.buyukkaya.chargingstationservice;

import com.buyukkaya.chargingstationservice.domain.model.entity.ChargingStation;
import com.buyukkaya.chargingstationservice.domain.model.enums.ChargerType;
import com.buyukkaya.chargingstationservice.domain.repository.ChargingStationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoRepositories
public class ChargingStationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChargingStationServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ChargingStationRepository chargingStationRepository) {
        return args -> {
            chargingStationRepository.save(ChargingStation.builder().availablePowerOutputs(Arrays.asList("3.7", "7.4", "11", "22"))
                    .chargerType(ChargerType.AC)
                    .build());
        };
    }

}
