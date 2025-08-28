package com.example.Anorak.API;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrainServiceValidationTests {

    private TrainService service;


    @BeforeEach
    public void setUp() {
        service = new TrainService(null, null, null);
    }
    @Test
    public void returnEmptyList_whenValidStation(){
        Station station = new Station("Liverpool Street");

        List<String> errors = new ArrayList<>(service.validateStation(station));

        assertTrue(errors.isEmpty());
    }

    @Test
    public void returnErrorsList_whenInvalidStation(){
        Station station = new Station();
        station.setName(null);
        List<String> errors = new ArrayList<>(service.validateStation(station));

        assertEquals(1, errors.size());
        assertEquals("Station name is missing", errors.get(0));
    }

    @Test
    public void returnErrorsList_whenStationNull(){
        Station station = null;
        List<String> errors = new ArrayList<>(service.validateStation(station));

        assertEquals(1, errors.size());
        assertEquals("Station is missing", errors.get(0));
    }

    @Test
    public void returnErrorsList_whenStationEmpty(){
        Station station = new Station();
        station.setName("");
        List<String> errors = new ArrayList<>(service.validateStation(station));

        assertEquals(1, errors.size());
        assertEquals("Station name is missing", errors.get(0));
    }

    @Test
    public void returnEmptyList_whenValidTrain(){
        Train train = new Train("Thomas", "Blue", "123");
        List<String> errors = new ArrayList<>(service.validateTrain(train));

        assertTrue(errors.isEmpty());
    }

    @Test
    public void returnErrorsList_whenInvalidTrainName(){
        Train train = new Train("t", "Blue", "123");
        List<String> errors = new ArrayList<>(service.validateTrain(train));

        assertEquals(1, errors.size());
        assertEquals("Train name must be between 2 and 100 characters", errors.get(0));

        train.setName("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678900000");
        errors = new ArrayList<>(service.validateTrain(train));
        assertEquals(1, errors.size());
        assertEquals("Train name must be between 2 and 100 characters", errors.get(0));
    }

    @Test
    public void returnErrorsList_whenTrainColourMissing(){
        Train train = new Train("Thomas", "", "123");
        List<String> errors = new ArrayList<>(service.validateTrain(train));

        assertEquals(1, errors.size());
        assertEquals("Train colour is missing", errors.get(0));

        train.setColour(null);
        errors = new ArrayList<>(service.validateTrain(train));
        assertEquals(1, errors.size());
        assertEquals("Train colour is missing", errors.get(0));
    }

    @Test
    public void returnErrorsList_whenTrainNumberMissing(){
        Train train = new Train("Thomas", "Blue", "");
        List<String> errors = new ArrayList<>(service.validateTrain(train));

        assertEquals(1, errors.size());
        assertEquals("Train number is missing", errors.get(0));

        train.setTrainNumber(null);
        errors = new ArrayList<>(service.validateTrain(train));
        assertEquals(1, errors.size());
        assertEquals("Train number is missing", errors.get(0));
    }

    @Test
    public void returnErrorsList_whenTrainEmpty(){
        Train train = null;

        List<String> errors = new ArrayList<>(service.validateTrain(train));
        assertEquals(1, errors.size());
        assertEquals("Train is missing", errors.get(0));
    }

    @Test
    public void returnErrorsList_whenTrainNameAndColourMissing(){
        Train train = new Train("", "", "123");
        List<String> errors = new ArrayList<>(service.validateTrain(train));
        assertEquals(2, errors.size());
        assertEquals("Train name must be between 2 and 100 characters", errors.get(0));
        assertEquals("Train colour is missing", errors.get(1));
    }

    @Test
    public void returnErrorsList_whenTrainNumberAndColourMissing(){
        Train train = new Train("Thomas", "", "");
        List<String> errors = new ArrayList<>(service.validateTrain(train));
        assertEquals(2, errors.size());
        assertEquals("Train colour is missing", errors.get(0));
        assertEquals("Train number is missing", errors.get(1));
    }

    @Test
    public void returnErrorsList_whenTrainNumberAndNameMissing(){
        Train train = new Train("", "Blue", "");
        List<String> errors = new ArrayList<>(service.validateTrain(train));

        assertEquals(2, errors.size());
        assertEquals("Train name must be between 2 and 100 characters", errors.get(0));
        assertEquals("Train number is missing", errors.get(1));
    }

    @Test
    public void returnErrorsList_whenTrainColourAndNameAndNumberMissing(){
        Train train = new Train("", "", "");
        List<String> errors = new ArrayList<>(service.validateTrain(train));
        assertEquals(3, errors.size());
        assertEquals("Train name must be between 2 and 100 characters", errors.get(0));
        assertEquals("Train colour is missing", errors.get(1));
        assertEquals("Train number is missing", errors.get(2));
    }

    @Test
    public void returnEmptyList_whenValidTimestamp(){
        Instant timestamp = Instant.now();
        List<String> errors = new ArrayList<>(service.validateTimestamp(timestamp));
        assertTrue(errors.isEmpty());
    }

    @Test
    public void returnErrorsList_whenEmptyTimestamp(){
        Instant timestamp = null;
        List<String> errors = new ArrayList<>(service.validateTimestamp(timestamp));

        assertEquals(1, errors.size());
        assertEquals("Timestamp is missing", errors.get(0));
    }

    @Test
    public void returnErrorsList_whenInvalidTimestamp(){
        Instant timestamp = Instant.now().plus(2, ChronoUnit.DAYS);
        List<String> errors = new ArrayList<>(service.validateTimestamp(timestamp));

        assertEquals(1, errors.size());
        assertEquals("Sighting timestamp cannot be in the future", errors.get(0));
    }



}
