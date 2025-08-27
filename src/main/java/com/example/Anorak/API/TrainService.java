package com.example.Anorak.API;

import com.example.Anorak.API.exceptions.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class TrainService {

    private final TrainRepository trainRepository;
    private final SightingRepository sightingRepository;
    private final StationRepository stationRepository;

    public TrainService(TrainRepository trainRepository, SightingRepository sightingRepository, StationRepository stationRepository) {
        this.trainRepository = trainRepository;
        this.sightingRepository = sightingRepository;
        this.stationRepository = stationRepository;
    }


    public List<Train> getAllTrains(){
        return trainRepository.findAll().collectList().block();
    }

    public Mono<Train> getTrainById(String id){
        return trainRepository.findById(id).switchIfEmpty(Mono.error(new TrainNotFoundException(id)));
    }


    public List<Sighting> getSightingsForTrain(String trainId){
        Train train = trainRepository.findById(trainId).block();

        Flux<Sighting> sightingsFlux = sightingRepository.findByTrainId(trainId);
        List<Sighting> sightings = sightingsFlux.collectList().block();

        for (Sighting sighting : sightings){
            Station station = stationRepository.findById(sighting.getStationId()).block();
            sighting.setStation(station);
            sighting.setTrain(train);
        }

        return sightings;
    }

    public void saveTrain(Train train){
        trainRepository.save(train);
    }

    public void saveSighting(Sighting sighting){
        sightingRepository.save(sighting);
    }

    public void saveStation(Station station){
        stationRepository.save(station);
    }


    public List<String> validateInput(List<Sighting> sightings){
        List<String> errors = new ArrayList<>();
        for (Sighting sighting : sightings){
            Train train = sighting.getTrain();
            Station station = sighting.getStation();


            if (train ==  null){
                errors.add("Train is missing.");

            }
            if (station == null){
                errors.add("Station is missing.");
            }
            String trainName = train.getName();
            if (trainName.length() < 2 || trainName.length() > 100){
                errors.add("Train name must be between 2 and 100 characters.");
            }
            String colour = train.getColour();
            if (colour == null || colour.isBlank()){
                errors.add("Colour is missing.");
            }

            String trainNumber = train.getTrainNumber();
            if (trainNumber == null){
                errors.add("Train number is missing.");
            }

            String stationName = station.getName();
            if (stationName == null){
                errors.add("Station name is missing.");
            }
        }
        return errors;
    }

    public Train getTrainByTrainNumber(String trainNumber){
        return trainRepository.findByTrainNumber(trainNumber).blockFirst();
    }

    public Station getStationByStationName(String stationName){
        return stationRepository.findByStationName(stationName).blockFirst();
    }


    public 




}
