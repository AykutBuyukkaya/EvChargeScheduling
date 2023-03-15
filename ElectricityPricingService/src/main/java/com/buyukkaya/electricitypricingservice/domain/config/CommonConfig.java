package com.buyukkaya.electricitypricingservice.domain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;

@Configuration
public class CommonConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


}
