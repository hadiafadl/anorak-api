package com.example.Anorak.API;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StationController {

    private final StationService stationService;
    private final TrainService trainService;

    public StationController(StationService stationService, TrainService trainService) {
        this.stationService = stationService;
        this.trainService = trainService;
    }

    @GetMapping("/station")
    public List<Station> getStations(){
        return this.stationService.getAllStations();
    }

    @PostMapping("/station")
    public Station addStation(@RequestBody Station station){
        return this.trainService.saveStation(station);
    }
}
