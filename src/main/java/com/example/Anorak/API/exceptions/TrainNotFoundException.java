package com.example.Anorak.API.exceptions;

public class TrainNotFoundException extends RuntimeException {

  public TrainNotFoundException(String id) {
    super("Could not find train with id: " + id);
  }
}
