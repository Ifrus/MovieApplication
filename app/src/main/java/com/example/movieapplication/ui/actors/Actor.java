package com.example.movieapplication.ui.actors;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Actor{

    private final String name;
    private final String profilePath;
    private boolean isSelected;

    public Actor(String name, String profilePath) {
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

