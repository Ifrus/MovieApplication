package com.example.movieapplication.ui.on_boarding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapplication.R;
import com.example.movieapplication.ui.actors.ActorsActivity;
import com.example.movieapplication.ui.genres.GenresActivity;
import com.example.movieapplication.ui.keywords.KeywordsActivity;

public class OnBoardingActivity extends AppCompatActivity {
    public static void open(Context context) {
        context.startActivity(new Intent(context, OnBoardingActivity.class));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_onboarding);
        setClickListener();
        Log.e("onboarding", "init");
    }

    private void setClickListener() {

        Button genresButton = findViewById(R.id.btnGenres);
        genresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoardingActivity.this, GenresActivity.class));
            }

        });

        Button actorsButton = findViewById(R.id.btnActors);
        actorsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoardingActivity.this, ActorsActivity.class));
            }
        });

        Button keywordsButton = findViewById(R.id.btnKeywords);
        keywordsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoardingActivity.this, KeywordsActivity.class));
            }
        });
    }

    @Override
    protected void onResume () {
        super.onResume();
    }
}

