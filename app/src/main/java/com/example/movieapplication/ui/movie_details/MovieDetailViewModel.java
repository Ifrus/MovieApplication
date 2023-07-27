package com.example.movieapplication.ui.movie_details;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapplication.database.interfaces.TMDBApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailViewModel extends ViewModel {
    private MutableLiveData<MovieDetail> movieDetailLiveData;
    private TMDBApiService tmdbApiService;

    public LiveData<MovieDetail> getMovieDetails(int movieId) {
        if (movieDetailLiveData == null) {
            movieDetailLiveData = new MutableLiveData<>();
            loadMovieDetails(movieId);
        }
        return movieDetailLiveData;
    }

    private void loadMovieDetails(int movieId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        tmdbApiService = retrofit.create(TMDBApiService.class);
        Call<MovieDetailResponse> call = tmdbApiService.getMovieDetails(movieId);

        call.enqueue(new Callback<MovieDetailResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieDetailResponse> call, @NonNull Response<MovieDetailResponse> response) {
                if (response.isSuccessful()) {
                    MovieDetailResponse movieDetailResponse = response.body();
                    if (movieDetailResponse != null) {
                        MovieDetail movieDetail = parseMovieDetailResponse(movieDetailResponse);
                        movieDetailLiveData.setValue(movieDetail);
                    } else {
                        Log.e("MovieDetailViewModel", "Response body is null");
                    }
                } else {
                    Log.e("MovieDetailViewModel", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieDetailResponse> call, @NonNull Throwable t) {
                Log.e("MovieDetailViewModel", "API call failed", t);
            }
        });
    }
    private MovieDetail parseMovieDetailResponse(MovieDetailResponse movieDetailResponse) {
        String title = movieDetailResponse.getTitle();
        String overview = movieDetailResponse.getOverview();
        String posterPath = movieDetailResponse.getPosterPath();
        String releaseDate = movieDetailResponse.getReleaseDate();

        List<String> videoUrls = new ArrayList<>();
        List<MovieDetailResponse.Video> videos = movieDetailResponse.getVideoResponse().getVideos();
        for (MovieDetailResponse.Video video : videos) {
            String videoUrl = "https://www.youtube.com/watch?v=" + video.getVideoKey();
            videoUrls.add(videoUrl);
        }

        return new MovieDetail( title, releaseDate,  posterPath,  overview, videoUrls);
    }
}


