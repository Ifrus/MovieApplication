package com.example.movieapplication.ui.actors;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapplication.database.interfaces.TMDBApiService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActorViewModel extends ViewModel {
    private MutableLiveData<List<Actor>> actorsLiveData;


    public LiveData<List<Actor>> getActors() {
        if(actorsLiveData == null){
            actorsLiveData = new MutableLiveData<>();
            loadActors();
        }
        return actorsLiveData;
    }

    private void loadActors() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TMDBApiService movieApiService = retrofit.create(TMDBApiService.class);
        Call<ActorResponse> call = movieApiService.getActors();

        call.enqueue(new Callback<ActorResponse>() {
            @Override
            public void onResponse(Call<ActorResponse> call, Response<ActorResponse> response) {
                if (response.isSuccessful()) {
                    ActorResponse actorResponse = response.body();
                        if (actorResponse != null) {
                            List<Actor> actors = actorResponse.getActors();
                            actorsLiveData.setValue(actors);
                    }
                } else {
                    // Handle the case when the response is not successful
                }
            }

            @Override
            public void onFailure(Call<ActorResponse> call, Throwable t) {
                // Handle the case when the request fails
            }
        });
    }
}

