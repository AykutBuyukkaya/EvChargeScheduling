package com.buyukkaya.chargeschedulingservice.domain.service.collector;

import com.buyukkaya.chargeschedulingservice.domain.exception.DataCollectorException;
import com.buyukkaya.chargeschedulingservice.domain.model.response.DataCollectorResponse;

import java.util.Optional;

public abstract class DataCollectorService {

    private DataCollectorService nextService;

    public void setNextService(DataCollectorService nextService) {
        this.nextService = nextService;
    }

    public DataCollectorService getNextService() {
        return nextService;
    }

    public abstract DataCollectorResponse collectData(DataCollectorResponse response);

    protected void checkOperationContinuity(DataCollectorResponse dataCollectorResponse) {
        Optional.ofNullable(dataCollectorResponse).orElseThrow(DataCollectorException::new);
    }

    protected String generateServiceRequestUrl(DataCollectorResponse dataCollectorResponse){
        Optional.ofNullable(dataCollectorResponse).orElseThrow(DataCollectorException::new);
        return "";
    }


}
