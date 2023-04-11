package com.buyukkaya.chargingstationservice.domain.exception;

public class TransformerNotFoundException extends RuntimeException{

    public TransformerNotFoundException() {
        super();
    }

    public TransformerNotFoundException(String message) {
        super(message);
    }
}
