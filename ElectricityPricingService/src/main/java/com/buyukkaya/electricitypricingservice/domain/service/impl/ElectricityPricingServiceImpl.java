package com.buyukkaya.electricitypricingservice.domain.service.impl;

import com.buyukkaya.electricitypricingservice.domain.exception.ExternalPricingDataException;
import com.buyukkaya.electricitypricingservice.domain.mapper.ElectricityPricingMapping;
import com.buyukkaya.electricitypricingservice.domain.model.dto.ElectricityPricingDataDto;
import com.buyukkaya.electricitypricingservice.domain.model.dto.ExternalElectricityPricingDataDto;
import com.buyukkaya.electricitypricingservice.domain.model.request.GetElectricityPriceRequest;
import com.buyukkaya.electricitypricingservice.domain.service.ElectricityPriceService;
import com.buyukkaya.electricitypricingservice.domain.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Primary
@Service("five-minute-pricing")
@Slf4j
public class ElectricityPricingServiceImpl implements ElectricityPriceService {

    private final CommonUtil commonUtil;
    private final RestTemplate restTemplate;
    private final ElectricityPricingMapping electricityPricingMapping;

    public ElectricityPricingServiceImpl(CommonUtil commonUtil, RestTemplate restTemplate, ElectricityPricingMapping electricityPricingMapping) {
        this.commonUtil = commonUtil;
        this.restTemplate = restTemplate;
        this.electricityPricingMapping = electricityPricingMapping;
    }

    @Override
    public List<ElectricityPricingDataDto> getElectricityPricingData(GetElectricityPriceRequest request) {
        ResponseEntity<List<ExternalElectricityPricingDataDto>> response = restTemplate.exchange(prepareRequestUrl(request), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        Optional<List<ExternalElectricityPricingDataDto>> maybeElectricityPricingDataDtoList = Optional.ofNullable(response.getBody());

        if (!response.getStatusCode().is2xxSuccessful() || maybeElectricityPricingDataDtoList.isEmpty()) {
            throw new ExternalPricingDataException("An error occurred during getting electricity data!");
        }

        final List<ElectricityPricingDataDto> electricityPricingDataDtoList = electricityPricingMapping.fromExternalList(response.getBody());

        Collections.reverse(electricityPricingDataDtoList);

        return electricityPricingDataDtoList;
    }

    private String prepareRequestUrl(GetElectricityPriceRequest request) {
        return String.format(commonUtil.getComedAPIUrl(), commonUtil.getFormat().format(request.getStartingDate().minusDays(1).minusMinutes(5)), commonUtil.getFormat().format(request.getEndingDate().minusDays(1).plusMinutes(5)));
    }

}
