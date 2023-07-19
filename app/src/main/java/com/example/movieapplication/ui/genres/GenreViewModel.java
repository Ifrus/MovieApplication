package com.example.movieapplication.ui.genres;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapplication.database.interfaces.TMDBApiService;
import com.example.movieapplication.ui.SharedPreferencesHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GenreViewModel extends ViewModel {

    private MutableLiveData<List<Genre>> genresLiveData;

    public LiveData<List<Genre>> getGenres(){
        if(genresLiveData == null){
            genresLiveData = new MutableLiveData<>();
            loadGenres();
        }

        return genresLiveData;
    }

   /* public void saveSelectedGenres(List<Genre> genres){
        Set<String> selectedGenres = new HashSet<>();
        for(Genre genre : genres){
            if(genre.isSelected()){
                selectedGenres.add(genre.getName());
            }
        }
        SharedPreferencesHelper.saveData(application, "selecte_genres", selectedGenres);
    }*/

    private void loadGenres(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TMDBApiService movieApiService = retrofit.create(TMDBApiService.class);
        Call<GenreResponse> call = movieApiService.getGenres();
       /* Set<String> selectedGenres = SharedPreferencesHelper.getData(application, "selected_genres", null);*/

        call.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                if (response.isSuccessful()) {
                    GenreResponse genreResponse = response.body();
                    if (genreResponse != null) {
                        List<Genre> genres = genreResponse.getGenres();
                        genresLiveData.setValue(genres);
                    }
                } else {
                    // Tratează cazul în care răspunsul nu este reușit
                }
            }
            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                // Tratează cazul în care solicitarea eșuează
            }
    });
}

}

