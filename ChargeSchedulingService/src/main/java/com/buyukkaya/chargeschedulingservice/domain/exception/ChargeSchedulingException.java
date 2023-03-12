package com.buyukkaya.chargeschedulingservice.domain.exception;

public class ChargeSchedulingException extends RuntimeException{

    public ChargeSchedulingException() {
        super();
    }

    public ChargeSchedulingException(String message) {
        super(message);
    }
}
