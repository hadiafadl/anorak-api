package com.example.Anorak.API;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;


@Document(collectionName = "trains")
public class Train {

    @DocumentId
    private String id;
    private String name;
    private String colour;
    private String trainNumber;


    public Train(){

    }
    public Train(String name, String colour, String trainNumber) {
        this.name = name;
        this.colour = colour;
        this.trainNumber = trainNumber;
    }


    public String getName(){
        return name;
    }

    public String getColour(){
        return colour;
    }

    public String getTrainNumber(){
        return trainNumber;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setColour(String colour){
        this.colour = colour;
    }

    public void setTrainNumber(String trainNumber){
        this.trainNumber = trainNumber;
    }
}
