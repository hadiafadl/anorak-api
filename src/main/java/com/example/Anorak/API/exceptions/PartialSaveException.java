package com.example.Anorak.API.exceptions;

import com.example.Anorak.API.Sighting;

import java.util.List;

public class PartialSaveException extends RuntimeException {
    private final List<Sighting> savedSightings;
    private final List<String> errors;

    public PartialSaveException(List<Sighting> savedSightings, List<String> errors) {
        super("Some sightings failed to save");
        this.savedSightings = savedSightings;
        this.errors = errors;
    }

    public List<Sighting> getSavedSightings() {
        return savedSightings;
    }

    public List<String> getErrors() {
        return errors;
    }
}

