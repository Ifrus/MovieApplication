package com.example.movieapplication.ui.preferences;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapplication.database.interfaces.TMDBApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PreferenceViewModel extends ViewModel {
    private MutableLiveData<List<Preference>> preferencesLiveData;
    private MutableLiveData<List<Preference>> moviesByQueryLiveData;

    private List<Preference> preferences;

    private String query;

    public LiveData<List<Preference>>  getPreferences() {
        if(preferencesLiveData == null){
            preferencesLiveData = new MutableLiveData<>();
            //loadPreferences();
        }
        return preferencesLiveData;
    }


//    public void setQuery(String query) {
//        this.query = query;
//    }

    public void loadMoviesByQuery(String query){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TMDBApiService movieApiService = retrofit.create(TMDBApiService.class);
        Call<PreferenceResponse> call = movieApiService.getMoviesByQuery(query);

        Log.d("PreferenceViewModel", "loadMoviesByQuery called with query: " + query);

        call.enqueue(new Callback<PreferenceResponse>() {
            @Override
            public void onResponse( @NonNull Call <PreferenceResponse> call, @NonNull Response<PreferenceResponse> response) {
                if (response.isSuccessful()) {
                    PreferenceResponse preferenceResponse = response.body();

                    if (preferenceResponse != null) {
                        List<Preference> preferences = preferenceResponse.getPreferences();
                        Log.d("PreferenceViewModel", "API response: " + preferences.toString());
                        preferencesLiveData.setValue(preferences);
                    } else {
                        Log.e("PreferenceViewModel", "Response body is null");
                    }
                } else {
                    Log.e("PreferenceViewModel", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<PreferenceResponse> call, Throwable t) {
                Log.e("PreferenceViewModel", "API call failed", t);
            }
        });
    }

    public void loadPreferences(String actors, String genres) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TMDBApiService movieApiService = retrofit.create(TMDBApiService.class);
        Call<PreferenceResponse> call = movieApiService.getMoviesByPrefs(actors, genres);


        call.enqueue(new Callback<PreferenceResponse>() {
            @Override
            public void onResponse(@NonNull Call<PreferenceResponse> call, @NonNull Response<PreferenceResponse> response) {
                if (response.isSuccessful()) {
                        PreferenceResponse preferenceResponse = response.body();
                    Log.d("PreferenceViewModel", "API response: " + response.body());
                        if (preferenceResponse != null) {
                            List<Preference> preferences = preferenceResponse.getPreferences();
                            for (Preference preference : preferences) {
                                preference.setFavorite(false);
                            }
                            preferencesLiveData.setValue(preferences);
                        }
                } else {
                    Log.e("PreferenceViewModel", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<PreferenceResponse> call, Throwable t) {
                Log.e("PreferenceViewModel", "API call failed", t);
            }
        });
    }
}

