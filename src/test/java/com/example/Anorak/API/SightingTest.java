package com.example.Anorak.API;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SightingTest {


    @Test
    @DisplayName("can create a train instance")
    public void testCanCreateTrain() {

        Train train = new Train("Thomas", "Blue", "11AB");
        train.setId("train-123");
        Station station = new Station("Liverpool Street");
        Instant timestamp = Instant.now();
        Sighting sighting = new Sighting(station, train, timestamp);

        assertEquals("train-123",  sighting.getTrainId());
        assertEquals("Liverpool Street", sighting.getStation().getName());
        assertEquals(timestamp, sighting.getTimestamp());

    }
}
