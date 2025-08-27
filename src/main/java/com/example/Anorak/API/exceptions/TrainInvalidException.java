package com.example.Anorak.API.exceptions;

public class TrainInvalidException extends RuntimeException {
  public TrainInvalidException() {
    super("Train object is missing, try again.");
  }
}
