package com.example.Anorak.API;


import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class SightingService {

    private final SightingRepository repository;

    public SightingService(SightingRepository repository) {
        this.repository = repository;
    }

    public List<Sighting> getAllSightings(){
        return repository.findAll().collectList().block();
    }

    public Flux<Sighting> getSightingsForTrain(String trainId){
        return repository.findByTrainId(trainId);
    }


}
