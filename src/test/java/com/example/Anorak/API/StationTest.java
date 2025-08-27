package com.example.Anorak.API;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StationTest {

    @Test
    @DisplayName("can create a station instance")
    public void testCanCreateStation() {

        Station station = new Station("Liverpool Street");
        assertNotNull(station);
        assertEquals("Liverpool Street", station.getName());

    }
}
