package com.example.movieapplication.ui.genres;

public class Genre {
    private int id;
    private String name;
    private boolean isSelected;

    public Genre(int id, String name){
        this.id=id;
        this.name=name;
    }

    public int getId(){return id; }

    public String getName(){return name; }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

