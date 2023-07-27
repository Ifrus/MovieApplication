package com.example.movieapplication.ui.on_boarding;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapplication.R;
import com.example.movieapplication.ui.actors.ActorsActivity;
import com.example.movieapplication.ui.genres.GenresActivity;
import com.example.movieapplication.ui.keywords.KeywordsActivity;
import com.example.movieapplication.ui.preferences.PreferencesActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class OnBoardingActivity extends AppCompatActivity {

    Button genresButton;
    Button actorsButton;
    Button keywordsButton;
    SharedPreferences sharedPreferences;

    public static void open(Context context) {
        context.startActivity(new Intent(context, OnBoardingActivity.class));
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_onboarding);

        genresButton = findViewById(R.id.btnGenres);
        actorsButton = findViewById(R.id.btnActors);
        keywordsButton = findViewById(R.id.btnKeywords);
        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);

        setClickListener();
        navigateToPreferencesActivity();
    }
    private void navigateToPreferencesActivity() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String selectedActorsJson = sharedPreferences.getString("selectedActors", null);
        String selectedGenresJson = sharedPreferences.getString("selectedGenres", null);
        Set<String> selectedKeywords = sharedPreferences.getStringSet("selectedKeywords", new HashSet<>());

        Gson gson = new Gson();
        Type actorType = new TypeToken<Set<Integer>>() {}.getType();
        Type genreType = new TypeToken<Set<Integer>>() {}.getType();

        Set<Integer> selectedActors = gson.fromJson(selectedActorsJson, actorType);
        Set<Integer> selectedGenres = gson.fromJson(selectedGenresJson, genreType);

        boolean hasActors = selectedActors != null && !selectedActors.isEmpty();
        boolean hasGenres = selectedGenres != null && !selectedGenres.isEmpty();

        if (hasActors && hasGenres && !selectedKeywords.isEmpty()) {
            Intent intent = new Intent(OnBoardingActivity.this, PreferencesActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please save items in all categories first!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setClickListener() {
        genresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoardingActivity.this, GenresActivity.class));
            }

        });

        actorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoardingActivity.this, ActorsActivity.class));
            }
        });

        keywordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoardingActivity.this, KeywordsActivity.class));
            }
        });
    }

    private void GenresCheck(){
        String selectedGenresJson = sharedPreferences.getString("selectedGenres", null);
        Set<Integer> selectedGenres = new HashSet<>();

        if (selectedGenresJson != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<Set<Integer>>() {}.getType();
            selectedGenres = gson.fromJson(selectedGenresJson, type);
        }

        if (!selectedGenres.isEmpty()) {
            genresButton.setVisibility(View.GONE);
        } else {
            genresButton.setVisibility(View.VISIBLE);
        }
    }

    private void ActorsCheck(){
        String selectedActorsJson = sharedPreferences.getString("selectedActors", null);
        Set<Integer> selectedActors = new HashSet<>();

        if (selectedActorsJson != null) {
            // Convert the JSON string to a List or Set of integers
            Gson gson = new Gson();
            Type type = new TypeToken<Set<Integer>>() {}.getType();
            selectedActors = gson.fromJson(selectedActorsJson, type);
        }

        if (!selectedActors.isEmpty()) {
            actorsButton.setVisibility(View.GONE);
        } else {
            actorsButton.setVisibility(View.VISIBLE);
        }
    }

    private void KeywordsCheck(){
        Set<String> selectedKeywords = sharedPreferences.getStringSet("selectedKeywords", new HashSet<>());

        if (!selectedKeywords.isEmpty()) {
            keywordsButton.setVisibility(View.GONE);
        } else {
            keywordsButton.setVisibility(View.VISIBLE);
        }
    }
    @Override
    protected void onResume () {
        super.onResume();
        GenresCheck();
        KeywordsCheck();
        ActorsCheck();
        navigateToPreferencesActivity();
    }
}

