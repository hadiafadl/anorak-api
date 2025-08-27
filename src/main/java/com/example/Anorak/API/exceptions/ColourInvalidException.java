package com.example.Anorak.API.exceptions;

public class ColourInvalidException extends RuntimeException {

    public ColourInvalidException(String colour) {
        super("Colour " + colour + " is invalid, try again.");
    }

}
