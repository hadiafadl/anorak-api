package com.example.Anorak.API.exceptions;

public class StationNameInvalidException extends RuntimeException {
    public StationNameInvalidException(String name) {

      super("Station name " + name + " is invalid, try again.");
    }
}
