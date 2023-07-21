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
        Log.e("onboarding", "init");
        navigateToPreferencesActivity();
    }
    private void navigateToPreferencesActivity() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        boolean hasActors = !sharedPreferences.getStringSet("selectedActors", new HashSet<>()).isEmpty();
        boolean hasGenres = !sharedPreferences.getStringSet("selectedGenres", new HashSet<>()).isEmpty();
        boolean hasKeywords = !sharedPreferences.getStringSet("selectedKeywords", new HashSet<>()).isEmpty();

        if (hasActors && hasGenres && hasKeywords) {
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
        Set<String> selectedGenres = sharedPreferences.getStringSet("selectedGenres", new HashSet<>());
        if (!selectedGenres.isEmpty()) {
            genresButton.setVisibility(View.GONE);
        } else {
            genresButton.setVisibility(View.VISIBLE);
        }
    }

    private void ActorsCheck(){
        Set<String> selectedActors = sharedPreferences.getStringSet("selectedActors", new HashSet<>());
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
    }
}

