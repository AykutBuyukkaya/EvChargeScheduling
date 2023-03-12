package com.buyukkaya.chargeschedulingservice.domain.exception;

public class ChargingNotSuitableException extends RuntimeException{

    public ChargingNotSuitableException() {
        super();
    }

    public ChargingNotSuitableException(String message) {
        super(message);
    }
}
