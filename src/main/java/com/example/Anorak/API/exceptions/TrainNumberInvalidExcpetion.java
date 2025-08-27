package com.example.Anorak.API.exceptions;

public class TrainNumberInvalidExcpetion extends RuntimeException {
    public TrainNumberInvalidExcpetion(String number) {
      super("Train number " + number + " is invalid, try again.");
    }
}
