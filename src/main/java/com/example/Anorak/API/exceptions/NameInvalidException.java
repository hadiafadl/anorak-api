package com.example.Anorak.API.exceptions;

public class NameInvalidException extends RuntimeException {

    public NameInvalidException(String name) {
        super(exceptionMessageHandler(name));
    }

    public static String exceptionMessageHandler(String name){

        if (name == null || name.isBlank()){
            return "Name cannot be null or blank. Try again";
        }
        else if (name.length() < 2){
            return "Name not long enough (minimum 2 characters). Try again";
        }
        else if (name.length() > 100){
            return "Name too long (maximum 100 characters). Try again";
        }
        return "Invalid name";
    }
}
