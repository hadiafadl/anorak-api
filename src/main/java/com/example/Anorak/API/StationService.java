package com.example.Anorak.API;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public List<Station> getAllStations(){
        return this.stationRepository.findAll().collectList().block();
    }

    public Station saveStation(Station station){
        return this.stationRepository.save(station).block();
    }
}
