package com.example.movieapplication.ui.actors;

import com.google.gson.annotations.SerializedName;

public class Actor {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("profile_path")
    private String profilePath;

    public int getId() {
        return id;
    }
    public Boolean isSelected;


    public String getName() {
        return name;
    }


    public String getImage() {
        return profilePath;
    }

    public Boolean getSelected() {
        return isSelected;
    }
}

