package com.example.movieapplication.ui.movie_details;

import java.util.List;

public class MovieDetail {
    private String title;
    private String releaseDate;
    private String posterUrl;
    private String overview;
    private List<String> videoUrls;

    public MovieDetail(String title, String releaseDate, String posterUrl, String overview, List<String> videoUrls) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
        this.overview = overview;
        this.videoUrls = videoUrls;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getOverview() {
        return overview;
    }

    public List<String> getVideoUrls() {
        return videoUrls;
    }

}

