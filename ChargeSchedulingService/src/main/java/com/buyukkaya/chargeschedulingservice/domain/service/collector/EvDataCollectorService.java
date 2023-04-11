package com.buyukkaya.chargeschedulingservice.domain.service.collector;

import com.buyukkaya.chargeschedulingservice.domain.exception.DataCollectorException;
import com.buyukkaya.chargeschedulingservice.domain.mapper.DataCollectorMapper;
import com.buyukkaya.chargeschedulingservice.domain.model.response.DataCollectorResponse;
import com.buyukkaya.chargeschedulingservice.domain.model.response.EvDataServiceResponse;
import com.buyukkaya.chargeschedulingservice.domain.service.collector.DataCollectorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EvDataCollectorService extends DataCollectorService {

    @Value("${data-collector-config.ev-data-service}")
    private String serviceUrl;
    private final RestTemplate restTemplate;
    private final DataCollectorMapper dataCollectorMapper;

    public EvDataCollectorService(RestTemplate restTemplate, DataCollectorMapper dataCollectorMapper) {
        this.restTemplate = restTemplate;
        this.dataCollectorMapper = dataCollectorMapper;
    }

    @Override
    public DataCollectorResponse collectData(DataCollectorResponse response) throws DataCollectorException {

        ResponseEntity<EvDataServiceResponse> evDataServiceResponse = restTemplate
                .exchange(generateServiceRequestUrl(response), HttpMethod.GET, null, EvDataServiceResponse.class);

        if (!evDataServiceResponse.getStatusCode().is2xxSuccessful()) {
            throw new DataCollectorException("Error during Ev Data collection");
        }

        dataCollectorMapper.mapEvDataServiceResponse(response, evDataServiceResponse.getBody());

        checkOperationContinuity(response);

        return this.getNextService() == null ? response : this.getNextService().collectData(response);
    }

    @Override
    protected String generateServiceRequestUrl(DataCollectorResponse dataCollectorResponse) {
        return String.format(serviceUrl.concat("/ev/%s"), dataCollectorResponse.getEvTypeId());
    }

}
