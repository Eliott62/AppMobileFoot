package com.example.appmobilefoot.presentation.model;

import java.io.Serializable;

public class Player implements Serializable{

    private String surname;
    private String name;
    private String position;
    private String nationality;
    private String club;
    private String imageURL;

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getNationality() {
        return nationality;
    }

    public String getClub() {
        return club;
    }

    public String getImageURL() {
        return imageURL;
    }
}

