package com.example.Anorak.API.exceptions;

import java.util.List;

public class InvalidSightingException extends RuntimeException {

    private final List<String> errors;
    public InvalidSightingException(List<String> errors) {

        super("Invalid sighting parameters.");
        this.errors = errors;

    }

    public List<String> getErrors(){
        return errors;
    }
}
