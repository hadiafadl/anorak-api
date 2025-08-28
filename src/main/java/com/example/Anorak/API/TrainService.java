package com.example.Anorak.API;

import com.example.Anorak.API.exceptions.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.time.Instant;
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

        if (train == null){
            throw new TrainNotFoundException(trainId);
        }

        Flux<Sighting> sightingsFlux = sightingRepository.findByTrainId(trainId);
        List<Sighting> sightings = sightingsFlux.collectList().block();

        for (Sighting sighting : sightings){
            Station station = stationRepository.findById(sighting.getStationId()).block();
            sighting.setStation(station);
            sighting.setTrain(train);
        }

        return sightings;
    }

    public Train saveTrain(Train train){
        List<String> errors = new ArrayList<>(validateTrain(train));
        if (!errors.isEmpty()){
            throw new SchemaValidationException(errors);
        }
        return trainRepository.save(train).block();
    }


    public Station saveStation(Station station){
        List<String> errors = new ArrayList<>(validateStation(station));
        if (!errors.isEmpty()) {
            throw new SchemaValidationException(errors);
        }
        return stationRepository.save(station).block();
    }


    public List<String> validateTrain(Train train){
        List<String> errors = new ArrayList<>();
        if (train == null){
            errors.add("Train is missing");
            return errors;
        }

        if (train.getName().length()< 2 || train.getName().length()>100){
            errors.add("Train name must be between 2 and 100 characters");
        }

        if (train.getColour() == null || train.getColour().isBlank()){
            errors.add("Train colour is missing");
        }

        if (train.getTrainNumber() == null || train.getTrainNumber().isBlank()){
            errors.add("Train number is missing");
        }

        return errors;
    }

    public List<String> validateStation(Station station){
        List<String> errors = new ArrayList<>();
        if (station == null){
            errors.add("Station is missing");
            return errors;
        }

        if (station.getName() == null || station.getName().isBlank()){
            errors.add("Station name is missing");
        }

        return errors;
    }

    public List<String> validateTimestamp(Instant timestamp){
        List<String> errors = new ArrayList<>();

        if (timestamp == null){
            errors.add("Timestamp is missing");
        }
        else{
            Instant now = Instant.now();
            if (timestamp.isAfter(now)){
                errors.add("Sighting timestamp cannot be in the future");
            }
        }
        return errors;

    }

    public List<String> validateSightings(Sighting sighting){
        List<String> errors = new ArrayList<>();

        errors.addAll(validateTrain(sighting.getTrain()));
        errors.addAll(validateStation(sighting.getStation()));
        errors.addAll(validateTimestamp(sighting.getTimestamp()));

        return errors;
    }


    public Train getTrainByTrainNumber(String trainNumber){
        return trainRepository.findByTrainNumber(trainNumber).blockFirst();
    }

    public Station getStationByStationName(String stationName){
        return stationRepository.findByName(stationName).blockFirst();
    }

    private Sighting saveSighting(Sighting sighting){
        Train train = getTrainByTrainNumber(sighting.getTrain().getTrainNumber());
        Station station = getStationByStationName(sighting.getStation().getName());

        if (train == null){
            train = saveTrain(sighting.getTrain());
        }

        if (station == null){
            station = saveStation(sighting.getStation());
        }
        Instant timestamp = sighting.getTimestamp();

        Sighting finalSighting = new Sighting(station, train, timestamp);
        return  sightingRepository.save(finalSighting).block();
    }

    public List<Sighting> saveSightings(List<Sighting> sightings) {
        List<Sighting> savedSightings = new ArrayList<>();
        List<String> errors = new ArrayList<>();


        for (Sighting sighting : sightings) {
            try {
                List<String> validationErrors = validateSightings(sighting);
                if (!validationErrors.isEmpty()) {
                    errors.addAll(validationErrors);
                    continue;
                }

                Sighting savedSighting = saveSighting(sighting);
                savedSighting.setTrain(sighting.getTrain());
                savedSighting.setStation(sighting.getStation());
                savedSightings.add(savedSighting);
            } catch (Exception e) {
                errors.add(e.getMessage());
            }
        }
        if (!errors.isEmpty()) {
            if (savedSightings.isEmpty()) {
                throw new SchemaValidationException(errors);
            }
            throw new PartialSaveException(savedSightings, errors);
        }
        return savedSightings;
    }




}
