package com.example.movieapplication.database.interfaces;

import com.example.movieapplication.ui.actors.ActorResponse;
import com.example.movieapplication.ui.genres.GenreResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TMDBApiService {
    @GET("person/popular?api_key=d773193a88ede0c03b5da21759b8dea6&language=en-US&page=1\n")
    Call<ActorResponse> getActors();

    @GET("genre/movie/list?api_key=d773193a88ede0c03b5da21759b8dea6&language=en-US")
    Call<GenreResponse> getGenres();
}
