package com.buyukkaya.electricitypricingservice.domain.service.impl;

import com.buyukkaya.electricitypricingservice.domain.exception.ExternalPricingDataException;
import com.buyukkaya.electricitypricingservice.domain.mapper.ElectricityPricingMapping;
import com.buyukkaya.electricitypricingservice.domain.model.dto.ElectricityPricingDataDto;
import com.buyukkaya.electricitypricingservice.domain.model.dto.ExternalElectricityPricingDataDto;
import com.buyukkaya.electricitypricingservice.domain.model.request.GetElectricityPriceRequest;
import com.buyukkaya.electricitypricingservice.domain.service.ElectricityPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Primary
@Service
@Slf4j
public class ElectricityPricingServiceImpl implements ElectricityPriceService {

    @Value("${comed.url}")
    private String comedAPIUrl;
    private static final String DATE_TIME_FORMAT = "yyyyMMddHHmm";
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    private final RestTemplate restTemplate;
    private final ElectricityPricingMapping electricityPricingMapping;

    public ElectricityPricingServiceImpl(RestTemplate restTemplate, ElectricityPricingMapping electricityPricingMapping) {
        this.restTemplate = restTemplate;
        this.electricityPricingMapping = electricityPricingMapping;
    }

    @Override
    public List<ElectricityPricingDataDto> getElectricityPricingData(GetElectricityPriceRequest request) {
        ResponseEntity<List<ExternalElectricityPricingDataDto>> response = restTemplate.exchange(prepareRequestUrl(request), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new ExternalPricingDataException("An error occurred during getting electricity data!");
        }

        return electricityPricingMapping.fromExternalList(response.getBody());
    }

    private String prepareRequestUrl(GetElectricityPriceRequest request) {
        //TODO: DONT MIND THIS MESS. I DONT KNOW HOW TO HANDLE TIME ZONES
        return String.format(comedAPIUrl, format.format(request.getStartingDate().minusHours(6)), format.format(request.getEndingDate().minusHours(6)));
    }

}
