package com.example.movieapplication.database.interfaces;

import com.example.movieapplication.ui.actors.ActorResponse;
import com.example.movieapplication.ui.genres.GenreResponse;
import com.example.movieapplication.ui.movie_details.MovieDetailResponse;
import com.example.movieapplication.ui.preferences.PreferenceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDBApiService {
    @GET("person/popular?api_key=d773193a88ede0c03b5da21759b8dea6&language=en-US&page=1")
    Call<ActorResponse> getActors();

    @GET("genre/movie/list?api_key=d773193a88ede0c03b5da21759b8dea6&language=en-US")
    Call<GenreResponse> getGenres();

    @GET("search/movie?api_key=d773193a88ede0c03b5da21759b8dea6&language=en-US&")
    Call<PreferenceResponse> getMoviesByQuery(@Query("query") String query);

    @GET("search/movie?api_key=d773193a88ede0c03b5da21759b8dea6&language=en-US&query=Avengers&page=1&include_adult=false")
    Call<PreferenceResponse>getMoviesByPrefs(@Query("with_cast") String cast, @Query("with_genres") String genres);

    @GET("movie/299534?api_key=d773193a88ede0c03b5da21759b8dea6&append_to_response=videos")
    Call<MovieDetailResponse>getMovieDetails(int movieId);
}
