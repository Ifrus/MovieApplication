package com.example.movieapplication.ui.actors;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Actor{

    private final int id;
    private final String name;
    private final String profile_path;
    private boolean isSelected;

    public Actor(String name, String profile_path, int id) {
        this.name = name;
        this.profile_path = profile_path;
        this.id = id;
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public String getImage() {
        return profile_path;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

}

