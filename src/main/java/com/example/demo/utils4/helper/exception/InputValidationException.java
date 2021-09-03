package com.example.demo.utils4.helper.exception;


public class InputValidationException extends Exception {

    private static final long serialVersionUID = 1L;

    public InputValidationException(String message) {
        super(message);
    }

    public InputValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
