package com.example.Anorak.API;


import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class TrainController {

    private final TrainService trainService;
    private final SightingService sightingService;

    public TrainController(TrainService trainService, SightingService sightingService) {
        this.trainService = trainService;
        this.sightingService = sightingService;
    }

    @GetMapping("/train")
    public List<Train> getTrains(){
        return trainService.getAllTrains();
    }

    @GetMapping("/train/{id}")
    public Train getTrain(@PathVariable String id){
        return trainService.getTrainById(id).block();
    }

    @GetMapping("/train/{id}/sightings")

    public List<Sighting> getSightings(@PathVariable String id){
        return trainService.getSightingsForTrain(id);
    }

    @PostMapping("/sightings")
    public List<Sighting> addSightings(@RequestBody List<Sighting> sightings){
        return trainService.saveSightings(sightings);
    }

    @GetMapping("/sightings")
    public List<Sighting> getSightings(){
        return sightingService.getAllSightings();
    }
}
