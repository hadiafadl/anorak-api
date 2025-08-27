package com.example.Anorak.API;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TrainTest {

    @Test
    @DisplayName("can create a train instance")
    public void testCanCreateTrain() {

        Train train = new Train("Thomas", "Blue", "T1192A");

        assertEquals("Thomas", train.getName());
        assertEquals("Blue", train.getColour());
        assertEquals("T1192A", train.getTrainNumber());

    }
}
