package com.example.movieapplication.ui.preferences;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PreferenceResponse {
    @SerializedName("results")
    private List<Preference> movies;

    public List<Preference> getPreferences() {
        return movies;
    }
}
