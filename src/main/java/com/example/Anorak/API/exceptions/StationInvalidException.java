package com.example.Anorak.API.exceptions;

public class StationInvalidException extends RuntimeException {
    public StationInvalidException() {
        super("Station object is missing, try again.");
    }
}
