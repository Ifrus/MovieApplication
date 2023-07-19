package com.example.movieapplication.ui.keywords;

import android.app.Application;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KeywordViewModel extends ViewModel {
    private MutableLiveData<Keyword> keywordLiveData;
    private SharedPreferences sharedPreferences;

    private static final String KEY_KEYWORDS = "keywords";


    public KeywordViewModel(@NonNull Application application, SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public LiveData<Keyword> getKeyword() {
        if (keywordLiveData == null) {
            keywordLiveData = new MutableLiveData<>();
            loadKeyword();
        }
        return keywordLiveData;
    }

    public void saveSelectedKeyword(Keyword keyword) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> keywordSet = new HashSet<>(keyword.getKeywordsList());
        editor.putStringSet(KEY_KEYWORDS, keywordSet);
        editor.apply();
        keywordLiveData.setValue(keyword);
    }

    private void loadKeyword() {
        Set<String> keywordSet = sharedPreferences.getStringSet(KEY_KEYWORDS, null);
        if (keywordSet != null) {
            List<String> keywordsList = new ArrayList<>(keywordSet);
            Keyword keyword = new Keyword();
            keyword.setKeywordsList(keywordsList);
            keywordLiveData.setValue(keyword);
        }
    }
}
