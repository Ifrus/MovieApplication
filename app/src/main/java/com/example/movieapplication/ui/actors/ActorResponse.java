package com.example.movieapplication.ui.actors;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ActorResponse {

    @SerializedName("results")
    private List<Actor> actors;
    public List<Actor> getActors() {
        return actors;
    }
}
