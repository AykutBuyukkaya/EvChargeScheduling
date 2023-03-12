package com.buyukkaya.electricitypricingservice.domain.exception;

public class ExternalPricingDataException extends RuntimeException{

    public ExternalPricingDataException() {
        super();
    }

    public ExternalPricingDataException(String message) {
        super(message);
    }
}
