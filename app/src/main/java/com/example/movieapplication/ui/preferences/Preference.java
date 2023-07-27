package com.example.movieapplication.ui.preferences;

import java.util.List;

public class Preference {
    private List<String> genres;
    private List<String> actors;
    private List<String> keywords;
    private String title;
    private String poster_path;
    private String overview;
    private String release_date;

    private boolean isFavorite;

    public Preference(String title, String poster_path, String overview, List<String> genres, List<String> actors, List<String> keywords, String release_date) {
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.genres = genres;
        this.actors = actors;
        this.keywords = keywords;
        this.release_date = release_date;
    }

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

    public String getYear() {
        return release_date;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}

