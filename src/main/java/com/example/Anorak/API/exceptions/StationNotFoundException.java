package com.example.Anorak.API.exceptions;

public class StationNotFoundException extends RuntimeException {

    public StationNotFoundException(String id) {
        super("Could not find station with id: " + id);
    }
}
