package com.example.Anorak.API.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(TrainNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse traintNotFoundHandler(TrainNotFoundException ex) {

        return new ErrorResponse("E404", ex.getMessage(), Collections.emptyList());
    }

    @ExceptionHandler(TrainInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse trainInvalidHandler(TrainInvalidException ex) {
        return new ErrorResponse("E001", "Train not provided", List.of(ex.getMessage()));
    }

    @ExceptionHandler(TrainNumberInvalidExcpetion.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse trainNumberInvalidHandler(TrainNumberInvalidExcpetion ex) {
       return new ErrorResponse("E002", "Train number not valid", List.of(ex.getMessage()));
    }

    @ExceptionHandler(ColourInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse trainColourInvalidHandler(ColourInvalidException ex) {
        return new ErrorResponse("E003", "Train colour not valid", List.of(ex.getMessage()));
    }

    @ExceptionHandler(StationInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse stationInvalidHandler(StationInvalidException ex) {
        return new ErrorResponse("E004", "Station not provided", List.of(ex.getMessage()));
    }


    @ExceptionHandler(StationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse stationNotFoundHandler(StationNotFoundException ex) {
        return new ErrorResponse("E005", "Station not found", List.of(ex.getMessage()));
    }

    @ExceptionHandler(NameInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse nameInvalidHandler(NameInvalidException ex) {
        return new ErrorResponse("E005", "Invalid train name", List.of(ex.getMessage()));
    }


    @ExceptionHandler(TimestampInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse timestampInvalidHandler(TimestampInvalidException ex){
        return new ErrorResponse("E006", "Timestamp not valid", List.of(ex.getMessage()));
    }

    @ExceptionHandler(StationNameInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse stationNameInvalidHandler(StationNameInvalidException ex){
        return new ErrorResponse("E0067", "Station name not valid", List.of(ex.getMessage()));
    }




}
