package com.example.movieapplication.ui.genres;

import android.content.Context;

import com.example.movieapplication.ui.SharedPreferencesHelper;

public class Genre {
    private int id;

    private String name;

    public Genre(int id, String name){
        this.id=id;
        this.name=name;
    }

    public int getId(){return id; }

    public String getName(){return name; }

 /*   public void saveData(Context context) {
        SharedPreferencesHelper.saveData(context, "genre_data", this);
    }*/
}

