package com.buyukkaya.chargeschedulingservice.domain.service.impl;

import com.buyukkaya.chargeschedulingservice.domain.exception.DataCollectorException;
import com.buyukkaya.chargeschedulingservice.domain.mapper.DataCollectorMapper;
import com.buyukkaya.chargeschedulingservice.domain.model.request.ElectricityPricingServiceRequest;
import com.buyukkaya.chargeschedulingservice.domain.model.response.DataCollectorResponse;
import com.buyukkaya.chargeschedulingservice.domain.model.response.ElectricityPricingServiceResponse;
import com.buyukkaya.chargeschedulingservice.domain.service.DataCollectorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ElectricityPricingDataCollectorService extends DataCollectorService {

    @Value("${data-collector-config.electricity-pricing-service}")
    private String serviceUrl;
    private final RestTemplate restTemplate;
    private final DataCollectorMapper dataCollectorMapper;

    public ElectricityPricingDataCollectorService(RestTemplate restTemplate, DataCollectorMapper dataCollectorMapper) {
        this.restTemplate = restTemplate;
        this.dataCollectorMapper = dataCollectorMapper;

    }

    @Override
    public DataCollectorResponse collectData(DataCollectorResponse response) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ElectricityPricingServiceRequest> httpEntity = new HttpEntity<>(dataCollectorMapper.toElectricityPricingServiceRequest(response),httpHeaders);


        ResponseEntity<ElectricityPricingServiceResponse> electricityPricingServiceResponse = restTemplate
                .exchange(generateServiceRequestUrl(response), HttpMethod.POST,
                        httpEntity,
                        ElectricityPricingServiceResponse.class);

        if (!electricityPricingServiceResponse.getStatusCode().is2xxSuccessful()) {
            throw new DataCollectorException("Error during Charging Station Data collection");
        }

        dataCollectorMapper.mapElectricityPricingServiceResponse(response, electricityPricingServiceResponse.getBody());

        checkOperationContinuity(response);

        return this.getNextService() == null ? response : this.getNextService().collectData(response);

    }

    @Override
    protected void checkOperationContinuity(DataCollectorResponse dataCollectorResponse) {
        super.checkOperationContinuity(dataCollectorResponse);
    }

    @Override
    protected String generateServiceRequestUrl(DataCollectorResponse dataCollectorResponse) {
        return this.serviceUrl.concat("/between-dates/one-minute-data");
    }
}
