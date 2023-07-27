package com.example.movieapplication.ui.movie_details;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetailResponse {
    @SerializedName("videos")
    private VideoResponse videoResponse;

    @SerializedName("title")
    private String title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("release_date")
    private String releaseDate;

    public VideoResponse getVideoResponse() {
        return videoResponse;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public class VideoResponse {
        @SerializedName("results")
        private List<Video> videos;

        public List<Video> getVideos() {
            return videos;
        }
    }

    public class Video {
        @SerializedName("key")
        private String videoKey;

        @SerializedName("name")
        private String videoName;

        public String getVideoKey() {
            return videoKey;
        }

        public String getVideoName() {
            return videoName;
        }
    }
}
