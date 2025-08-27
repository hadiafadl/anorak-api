package com.example.Anorak.API;

import com.example.Anorak.API.Train;
import com.example.Anorak.API.exceptions.TrainNotFoundException;
import com.example.Anorak.API.TrainRepository;
import com.example.Anorak.API.TrainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.RequestEntity.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)

@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class TrainServiceTests {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private TrainService trainService;

    @MockitoBean
    private SightingService sightingService;


    @Test
    public void returnTrains_whenGetTrains_thenStatus200() throws Exception {

        Train train = new Train("Thomas", "Blue", "ABC12");
        Train train2 = new Train("Delilah", "Black", "XYZ999");

        when(trainService.getAllTrains()).thenReturn(List.of(train, train2));
        mvc.perform(MockMvcRequestBuilders.get("/train")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Thomas"))
                .andExpect(jsonPath("$[1].name").value("Delilah"))
                .andExpect(jsonPath("$[0].colour").value("Blue"))
                .andExpect(jsonPath("$[1].colour").value("Black"))
                .andExpect(jsonPath("$[0].trainNumber").value("ABC12"))
                .andExpect(jsonPath("$[1].trainNumber").value("XYZ999"));
    }

    @Test
    public void returnTrain_whenGetTrainById_thenStatus200() throws Exception {
        Train train = new Train("Thomas", "Blue", "ABC12");
        Train train2 = new Train("Delilah", "Black", "XYZ999");
        train.setId("123");
        train2.setId("789");

        when(trainService.getTrainById("123")).thenReturn(Mono.just(train));

        mvc.perform(MockMvcRequestBuilders.get("/train/123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Thomas"));


        when(trainService.getTrainById("789")).thenReturn(Mono.just(train2));

        mvc.perform(MockMvcRequestBuilders.get("/train/789")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.colour").value("Black"));
    }

    @Test
    public void returnError_whenGetTrainByInvalidId_thenStatus404() throws Exception {

        when(trainService.getTrainById("id-003")).thenThrow(TrainNotFoundException.class);

        mvc.perform(MockMvcRequestBuilders.get("/train/id-003")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void returnSightings_whenGetSightingsByTrain_thenStatus200() throws Exception {

        Train train = new Train("Thomas", "Blue", "ABC123");
        train.setId("123");

        Station station = new Station("Liverpool Street");
        station.setId("456");

        Instant timestamp = Instant.parse("2025-08-26T09:30:00.490Z");

        Sighting sighting = new Sighting(station, train, timestamp);
        sighting.setTrain(train);
        sighting.setStation(station);

        when(trainService.getSightingsForTrain("123"))
                .thenReturn(List.of(sighting));

        mvc.perform(MockMvcRequestBuilders.get("/train/123/sightings")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].station.name").value("Liverpool Street"))
                .andExpect(jsonPath("$[0].train.colour").value("Blue"))
                .andExpect(jsonPath("$[0].train.trainNumber").value("ABC123"));
    }



}
