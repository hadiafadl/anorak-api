package com.example.Anorak.API.exceptions;

public class TimestampInvalidException extends RuntimeException {
    public TimestampInvalidException() {
        super("Timestamp object is missing or invalid, try again.");
    }
}
