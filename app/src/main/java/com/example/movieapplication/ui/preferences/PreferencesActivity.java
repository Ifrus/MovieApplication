package com.example.movieapplication.ui.preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapplication.R;
import com.example.movieapplication.ui.actors.Actor;
import com.example.movieapplication.ui.genres.Genre;
import com.example.movieapplication.ui.movie_details.MovieDetailsActivity;
import com.example.movieapplication.ui.saved_movies.SavedMoviesActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PreferencesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PreferenceAdapter preferenceAdapter;
    private PreferenceViewModel preferenceViewModel;
    private DrawerLayout drawerLayout;

    private EditText searchEditText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_preferences);

        recyclerView = findViewById(R.id.recyclerViewPreferences);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        preferenceAdapter = new PreferenceAdapter();
        recyclerView.setAdapter(preferenceAdapter);

        searchEditText = findViewById(R.id.searchEditText);

        List<Preference> samplePreferences = new ArrayList<>();
        preferenceAdapter.setPreferences(samplePreferences);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        preferenceViewModel = new ViewModelProvider(this).get(PreferenceViewModel.class);

        String actors = getActors();
        String genres = getGenres();
        preferenceViewModel.loadPreferences(actors, genres);


        preferenceViewModel.getPreferences().observe(this, new Observer<List<Preference>>() {
            @Override
            public void onChanged(List<Preference> preferences) {
                if (preferences != null) {
                    preferenceAdapter.setPreferences(preferences);
                }
            }
        });

        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    performSearch(searchEditText.getText().toString());
                    return true;
                }
                return false;
            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                performSearch(charSequence.toString());
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_saved_movies) {
                    openSavedMoviesActivity();
                } else if (itemId == R.id.menu_preferences) {}
                DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        getActors();
        getGenres();

        preferenceAdapter.setOnItemClickListener(new PreferenceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Preference preference) {
                Intent intent = new Intent(PreferencesActivity.this, MovieDetailsActivity.class);
                intent.putExtra("movieTitle", preference.getTitle());
                intent.putExtra("posterUrl", preference.getPosterUrl());
                intent.putExtra("overview", preference.getMovieOverview());
                intent.putExtra("releaseDate", preference.getYear());
                startActivity(intent);
            }
        });
    }


    public void performSearch(String query){
        Log.d("PreferencesActivity", "performSearch called with query: " + query);
        getMoviesByQuery(query);
    }

    public void getMoviesByQuery(String query){
        preferenceViewModel = new ViewModelProvider(this).get(PreferenceViewModel.class);
        preferenceViewModel.loadMoviesByQuery(query);
    }

    //returns a set of selected actor IDs.
    private Set<Integer> selectedActors() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String selectedActorsJson = sharedPreferences.getString("selectedActors", null);

        Set<Integer> selectedActors = new HashSet<>();

        if (selectedActorsJson != null && !selectedActorsJson.isEmpty()) {
            Gson gson = new Gson();
            Type type = new TypeToken<Set<Integer>>() {}.getType();
            selectedActors = gson.fromJson(selectedActorsJson, type);
        }
        return selectedActors;
    }

    private Set<Integer> selectedGenres() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String selectedGenresJson = sharedPreferences.getString("selectedGenres", null);

        Set<Integer> selectedGenres = new HashSet<>();

        if (selectedGenresJson != null && !selectedGenresJson.isEmpty()) {
            Gson gson = new Gson();
            Type type = new TypeToken<Set<Integer>>() {}.getType();
            selectedGenres = gson.fromJson(selectedGenresJson, type);
        }
        return selectedGenres;
    }

    private String getActors() {
        Set<Integer> selectedActors = selectedActors();
        List<String> actorIds = new ArrayList<>();

        for (Integer actorId : selectedActors) {
            actorIds.add(String.valueOf(actorId));
        }

        return TextUtils.join(",", actorIds);
    }

    //returns a comma-separated string representation of selected genre IDs
    private String getGenres() {
        Set<Integer> selectedGenres = selectedGenres();
        List<String> genreIds = new ArrayList<>();

        for (Integer genreId : selectedGenres) {
            genreIds.add(String.valueOf(genreId));
        }

        return TextUtils.join(",", genreIds);
    }
    private void openSavedMoviesActivity() {
        Intent intent = new Intent(this, SavedMoviesActivity.class);
        startActivity(intent);
    }

    private void openMovieDetailsActivity(int movieId) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("movie_id", movieId);
        startActivity(intent);
    }

}

