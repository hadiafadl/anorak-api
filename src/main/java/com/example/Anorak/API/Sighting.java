package com.example.Anorak.API;

import java.time.Instant;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;
import org.springframework.data.annotation.Transient;


@Document(collectionName = "sightings")
public class Sighting {

    @DocumentId
    private String id;
    @JsonIgnore
    private String stationId;
    @JsonIgnore
    private String trainId;
    private Instant timestamp;

    @Transient
    private Train train;

    @Transient
    private Station station;

    public Sighting(){

    }

    public Sighting(Station station, Train train, Instant timestamp){
        this.stationId = station.getId();
        this.trainId = train.getId();
        this.timestamp = timestamp;
    }

    public Station getStation() {
        return station;
    }

    public String getTrainId() {
        return trainId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Train getTrain() {
        return train;
    }

    public String getStationId() {
        return stationId;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
    public void setStation(Station station) {
        this.station = station;
    }
    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

}
