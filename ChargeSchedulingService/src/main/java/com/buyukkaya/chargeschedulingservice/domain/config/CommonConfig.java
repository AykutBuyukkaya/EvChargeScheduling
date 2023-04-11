package com.buyukkaya.chargeschedulingservice.domain.config;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CommonConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
