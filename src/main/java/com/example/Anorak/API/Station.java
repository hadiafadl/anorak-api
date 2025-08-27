package com.example.Anorak.API;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;


@Document(collectionName = "stations")
public class Station {

    @DocumentId
    private String id;
    private String name;

    public Station(){

    }

    public Station(String name){
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
