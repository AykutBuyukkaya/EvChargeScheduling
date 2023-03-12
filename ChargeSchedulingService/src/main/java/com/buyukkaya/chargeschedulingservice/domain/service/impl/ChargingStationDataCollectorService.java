package com.buyukkaya.chargeschedulingservice.domain.service.impl;

import com.buyukkaya.chargeschedulingservice.domain.exception.ChargingNotSuitableException;
import com.buyukkaya.chargeschedulingservice.domain.exception.DataCollectorException;
import com.buyukkaya.chargeschedulingservice.domain.mapper.DataCollectorMapper;
import com.buyukkaya.chargeschedulingservice.domain.model.response.ChargingStationServiceResponse;
import com.buyukkaya.chargeschedulingservice.domain.model.response.DataCollectorResponse;
import com.buyukkaya.chargeschedulingservice.domain.service.DataCollectorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class ChargingStationDataCollectorService extends DataCollectorService {

    @Value("${data-collector-config.charging-station-service}")
    private String serviceUrl;
    private final RestTemplate restTemplate;
    private final DataCollectorMapper dataCollectorMapper;

    public ChargingStationDataCollectorService(RestTemplate restTemplate, DataCollectorMapper dataCollectorMapper) {
        this.restTemplate = restTemplate;
        this.dataCollectorMapper = dataCollectorMapper;
    }

    @Override
    public DataCollectorResponse collectData(DataCollectorResponse response) {

        ResponseEntity<ChargingStationServiceResponse> chargingStationServiceResponse = restTemplate
                .getForEntity(generateServiceRequestUrl(response), ChargingStationServiceResponse.class);

        if (!chargingStationServiceResponse.getStatusCode().is2xxSuccessful()) {
            throw new DataCollectorException("Error during Charging Station Data collection");
        }

        dataCollectorMapper.mapChargingStationServiceResponse(response, chargingStationServiceResponse.getBody());

        checkOperationContinuity(response);

        return this.getNextService() == null ? response : this.getNextService().collectData(response);
    }

    @Override
    protected void checkOperationContinuity(DataCollectorResponse dataCollectorResponse) {
        super.checkOperationContinuity(dataCollectorResponse);

        final Map<String, Double> evChargingPowerMap = dataCollectorResponse.getEvDataServiceResponse().getEv().getAcCharger().getPowerPerChargingPoint();
        final List<String> chargingStationPowerOutput = dataCollectorResponse.getChargingStationServiceResponse().getChargingStation().getAvailablePowerOutputs();
        // TODO CHECK CHARGING STATION AVAILABILITY.
        if (chargingStationPowerOutput.stream().noneMatch(evChargingPowerMap::containsKey)) {
            throw new ChargingNotSuitableException("Selected charging station does not contain required charging power to charge selected EV!");
        }

    }

    @Override
    protected String generateServiceRequestUrl(DataCollectorResponse dataCollectorResponse) {
        return String.format(this.serviceUrl.concat("/%s"), dataCollectorResponse.getChargingStationId());
    }
}
