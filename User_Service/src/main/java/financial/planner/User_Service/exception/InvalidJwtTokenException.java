package com.rohit200400.busticketbooking.userservice.exception;

public class InvalidJwtTokenException extends Exception{
    public InvalidJwtTokenException(String message) {
        super(message);
    }
}
