package com.example.movieapplication.ui.keywords;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapplication.database.interfaces.TMDBApiService;
import com.example.movieapplication.ui.genres.Genre;
import com.example.movieapplication.ui.genres.GenreResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KeywordViewModel extends ViewModel {
    private SharedPreferences sharedPreferences;
    private MutableLiveData<List<String>> keywordsLiveData;

    public KeywordViewModel(Application application) {
        super();
        sharedPreferences = application.getSharedPreferences("MyKeywords", Context.MODE_PRIVATE);
        keywordsLiveData = new MutableLiveData<>();
    }

    public LiveData<List<String>> getKeywordsLiveData() {
        if (keywordsLiveData.getValue() == null) {
            loadKeywords();
        }
        return keywordsLiveData;
    }

    private void loadKeywords() {
        String keywordsString = sharedPreferences.getString("keywords", "");
        List<String> keywordsList = Arrays.asList(keywordsString.split(";"));
        keywordsLiveData.setValue(keywordsList);
    }

    public void saveKeywords(List<String> keywordsList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String keyword : keywordsList) {
            stringBuilder.append(keyword).append(";");
        }

        String keywordsString = stringBuilder.toString().trim();
        if (keywordsString.endsWith(";")) {
            keywordsString = keywordsString.substring(0, keywordsString.length() - 1);
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("keywords", keywordsString);
        editor.apply();
        keywordsLiveData.setValue(keywordsList);
    }
}
