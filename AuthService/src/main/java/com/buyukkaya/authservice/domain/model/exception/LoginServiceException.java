package com.buyukkaya.authservice.domain.model.exception;

public class LoginServiceException extends RuntimeException{

    public LoginServiceException() {
        super();
    }

    public LoginServiceException(String message) {
        super(message);
    }
}
