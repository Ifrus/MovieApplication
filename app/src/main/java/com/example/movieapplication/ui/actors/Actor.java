package com.example.movieapplication.ui.actors;

import com.google.gson.annotations.SerializedName;

public class Actor {

   // @SerializedName("name")
    private final String name;

   // @SerializedName("profile_path")
    private final String profilePath;

    public Boolean isSelected;

    public Actor(String name, String profilePath){
        this.name = name;
        this.profilePath = profilePath;
    }


    public String getName() {
        return name;
    }


    public String getImage() {
        return profilePath;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

