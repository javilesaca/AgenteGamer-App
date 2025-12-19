package com.miapp.agentegamer.data.remote.model;

import com.google.gson.annotations.SerializedName;

public class GameDto {

    private int id;
    private String name;

    @SerializedName("released")
    private String releaseDate;

    @SerializedName("background_image")
    private String imageUrl;

    private double rating;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReleaseDate(){
        return releaseDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getRating() {
        return rating;
    }
}
