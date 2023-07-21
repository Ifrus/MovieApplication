package com.example.movieapplication.ui.preferences;

import java.util.List;

public class Preference {
    private List<String> genres;
    private List<String> actors;
    private List<String> keywords;
    private String title;
    private String poster_path;;
    private String overview;

    public List<String> getGenres() {
        return genres;
    }

    public List<String> getActors() {
        return actors;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public String getTitle() {
        return title;
    }

    public String getMovieTitle() {
        return title;
    }

    public void setMovieTitle(String title) {
        this.title = title;
    }

    public String getMovieOverview() {
        return overview;
    }

    public void setMovieOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterUrl() {
        return poster_path;
    }

    public void setPosterUrl(String poster_path) {
        this.poster_path = poster_path;
    }
}

