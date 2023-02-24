package com.buyukkaya.evdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories
public class EvDataServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvDataServiceApplication.class, args);
    }

}
