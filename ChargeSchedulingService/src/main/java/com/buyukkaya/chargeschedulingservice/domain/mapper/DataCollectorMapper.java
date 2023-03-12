package com.buyukkaya.chargeschedulingservice.domain.mapper;

import com.buyukkaya.chargeschedulingservice.domain.model.request.ChargeSchedulingRequest;
import com.buyukkaya.chargeschedulingservice.domain.model.request.ElectricityPricingServiceRequest;
import com.buyukkaya.chargeschedulingservice.domain.model.response.ChargingStationServiceResponse;
import com.buyukkaya.chargeschedulingservice.domain.model.response.DataCollectorResponse;
import com.buyukkaya.chargeschedulingservice.domain.model.response.ElectricityPricingServiceResponse;
import com.buyukkaya.chargeschedulingservice.domain.model.response.EvDataServiceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface DataCollectorMapper {

    DataCollectorResponse fromChargeSchedulingRequest(ChargeSchedulingRequest chargeSchedulingRequest);

    @Mapping(target = "startingDate", source = "arrivalTime")
    @Mapping(target = "endingDate", source = "departureTime")
    ElectricityPricingServiceRequest toElectricityPricingServiceRequest(DataCollectorResponse dataCollectorResponse);

    @Mapping(target = "evDataServiceResponse", source = ".")
    void mapEvDataServiceResponse(@MappingTarget DataCollectorResponse dataCollectorResponse, EvDataServiceResponse evDataServiceResponse);

    @Mapping(target = "chargingStationServiceResponse", source = ".")
    void mapChargingStationServiceResponse(@MappingTarget DataCollectorResponse dataCollectorResponse, ChargingStationServiceResponse chargingStationServiceResponse);

    @Mapping(target = "electricityPricingServiceResponse", source = ".")
    void mapElectricityPricingServiceResponse(@MappingTarget DataCollectorResponse dataCollectorResponse, ElectricityPricingServiceResponse electricityPricingServiceResponse);
}
