package com.buyukkaya.chargingstationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoRepositories
public class ChargingStationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChargingStationServiceApplication.class, args);
    }

}
