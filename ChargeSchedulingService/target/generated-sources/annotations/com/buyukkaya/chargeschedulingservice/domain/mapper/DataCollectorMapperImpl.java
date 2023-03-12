package com.buyukkaya.chargeschedulingservice.domain.mapper;

import com.buyukkaya.chargeschedulingservice.domain.model.request.ChargeSchedulingRequest;
import com.buyukkaya.chargeschedulingservice.domain.model.request.ElectricityPricingServiceRequest;
import com.buyukkaya.chargeschedulingservice.domain.model.response.ChargingStationServiceResponse;
import com.buyukkaya.chargeschedulingservice.domain.model.response.DataCollectorResponse;
import com.buyukkaya.chargeschedulingservice.domain.model.response.ElectricityPricingServiceResponse;
import com.buyukkaya.chargeschedulingservice.domain.model.response.EvDataServiceResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-11T12:40:24+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class DataCollectorMapperImpl implements DataCollectorMapper {

    @Override
    public DataCollectorResponse fromChargeSchedulingRequest(ChargeSchedulingRequest chargeSchedulingRequest) {
        if ( chargeSchedulingRequest == null ) {
            return null;
        }

        DataCollectorResponse.DataCollectorResponseBuilder dataCollectorResponse = DataCollectorResponse.builder();

        dataCollectorResponse.evTypeId( chargeSchedulingRequest.getEvTypeId() );
        dataCollectorResponse.chargingStationId( chargeSchedulingRequest.getChargingStationId() );
        dataCollectorResponse.arrivalTime( chargeSchedulingRequest.getArrivalTime() );
        dataCollectorResponse.departureTime( chargeSchedulingRequest.getDepartureTime() );
        dataCollectorResponse.currentSoC( chargeSchedulingRequest.getCurrentSoC() );
        dataCollectorResponse.expectedSoC( chargeSchedulingRequest.getExpectedSoC() );

        return dataCollectorResponse.build();
    }

    @Override
    public ElectricityPricingServiceRequest toElectricityPricingServiceRequest(DataCollectorResponse dataCollectorResponse) {
        if ( dataCollectorResponse == null ) {
            return null;
        }

        ElectricityPricingServiceRequest.ElectricityPricingServiceRequestBuilder electricityPricingServiceRequest = ElectricityPricingServiceRequest.builder();

        electricityPricingServiceRequest.startingDate( dataCollectorResponse.getArrivalTime() );
        electricityPricingServiceRequest.endingDate( dataCollectorResponse.getDepartureTime() );

        return electricityPricingServiceRequest.build();
    }

    @Override
    public void mapEvDataServiceResponse(DataCollectorResponse dataCollectorResponse, EvDataServiceResponse evDataServiceResponse) {
        if ( evDataServiceResponse == null ) {
            return;
        }

        dataCollectorResponse.setEvDataServiceResponse( evDataServiceResponse );
    }

    @Override
    public void mapChargingStationServiceResponse(DataCollectorResponse dataCollectorResponse, ChargingStationServiceResponse chargingStationServiceResponse) {
        if ( chargingStationServiceResponse == null ) {
            return;
        }

        dataCollectorResponse.setChargingStationServiceResponse( chargingStationServiceResponse );
    }

    @Override
    public void mapElectricityPricingServiceResponse(DataCollectorResponse dataCollectorResponse, ElectricityPricingServiceResponse electricityPricingServiceResponse) {
        if ( electricityPricingServiceResponse == null ) {
            return;
        }

        dataCollectorResponse.setElectricityPricingServiceResponse( electricityPricingServiceResponse );
    }
}
