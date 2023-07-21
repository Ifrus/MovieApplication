package com.example.movieapplication.ui.preferences;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapplication.database.interfaces.TMDBApiService;
import com.example.movieapplication.ui.genres.Genre;
import com.example.movieapplication.ui.genres.GenreResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PreferenceViewModel extends ViewModel {
    private MutableLiveData<List<Preference>> preferencesLiveData = new MutableLiveData<>();

    public LiveData<List<Preference>> getFilteredMoviesData() {
        return preferencesLiveData;
    }

    public void loadFilteredMovies(List<String> selectedGenres, List<String> selectedActors, List<String> selectedKeywords) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TMDBApiService movieApiService = retrofit.create(TMDBApiService.class);
        Call<PreferenceResponse> call = movieApiService.getPreferences();

        call.enqueue(new Callback<PreferenceResponse>() {
            @Override
            public void onResponse(Call<PreferenceResponse> call, Response<PreferenceResponse> response) {
                if (response.isSuccessful()) {
                    PreferenceResponse preferenceResponse = response.body();
                    if (preferenceResponse != null) {
                        List<Preference> allPreferences = preferenceResponse.getPreferences();

                        // Filter the movies based on saved preferences
                        List<Preference> filteredPreferences = filterPreferences(allPreferences, selectedGenres, selectedActors, selectedKeywords);
                        preferencesLiveData.setValue(filteredPreferences);
                    }
                } else {
                    Log.e("PreferenceViewModel", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<PreferenceResponse> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private List<Preference> filterPreferences(List<Preference> allPreferences, List<String> selectedGenres, List<String> selectedActors, List<String> selectedKeywords) {
        List<Preference> filteredPreferences = new ArrayList<>();
        for (Preference preference : allPreferences) {
            if (matchesGenres(preference, selectedGenres) && matchesActors(preference, selectedActors) && matchesKeywords(preference, selectedKeywords)) {
                filteredPreferences.add(preference);
            }
        }
        return filteredPreferences;
    }

    private boolean matchesGenres(Preference preference, List<String> selectedGenres) {
        List<String> preferenceGenres = preference.getGenres();

        // Check if any of the preference's genres match the selectedGenres
        for (String selectedGenre : selectedGenres) {
            if (preferenceGenres.contains(selectedGenre)) {
                return true;
            }
        }

        return false;
    }

    private boolean matchesActors(Preference preference, List<String> selectedActors) {
        List<String> preferenceActors = preference.getActors();

        // Check if any of the preference's actors match the selectedActors
        for (String selectedActor : selectedActors) {
            if (preferenceActors.contains(selectedActor)) {
                return true;
            }
        }

        return false;
    }

    private boolean matchesKeywords(Preference preference, List<String> selectedKeywords) {
        List<String> preferenceKeywords = preference.getKeywords();

        // Check if any of the preference's keywords match the selectedKeywords
        for (String selectedKeyword : selectedKeywords) {
            if (preferenceKeywords.contains(selectedKeyword)) {
                return true;
            }
        }

        return false;
    }
}
