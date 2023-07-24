package com.example.movieapplication.ui.preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

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
import com.example.movieapplication.ui.actors.ActorAdapter;
import com.example.movieapplication.ui.actors.ActorViewModel;
import com.example.movieapplication.ui.saved_movies.SavedMoviesActivity;
import com.example.movieapplication.ui.search_movies.SearchMoviesActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PreferencesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PreferenceAdapter preferenceAdapter;
    private PreferenceViewModel preferenceViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_preferences);

        recyclerView = findViewById(R.id.recyclerViewPreferences);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        preferenceAdapter = new PreferenceAdapter();
        recyclerView.setAdapter(preferenceAdapter);

        List<Preference> samplePreferences = new ArrayList<>();
        samplePreferences.add(new Preference("Movie 1", "poster_path_1", "Overview of Movie 1", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        samplePreferences.add(new Preference("Movie 2", "poster_path_2", "Overview of Movie 2", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        preferenceAdapter.setPreferences(samplePreferences);

        preferenceViewModel = new ViewModelProvider(this).get(PreferenceViewModel.class);
        preferenceViewModel = new ViewModelProvider(this).get(PreferenceViewModel.class);
        preferenceViewModel.getPreferences().observe(this, new Observer<List<Preference>>() {
            @Override
            public void onChanged(List<Preference> preferences) {
                if (preferences != null) {
                    preferenceAdapter.setPreferences(preferences);
                }
            }
        });
    }
}

  //  @Override
   /* protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_preferences);

        recyclerView = findViewById(R.id.recyclerViewPreferences);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        preferenceAdapter = new PreferenceAdapter();
        recyclerView.setAdapter(preferenceAdapter);

        DrawerLayout drawerLayout;
        NavigationView navigationView;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create a ViewModel instance
        preferenceViewModel = new ViewModelProvider(this).get(PreferenceViewModel.class);

        // Observe changes in the filtered movies data
        preferenceViewModel.getFilteredMoviesData().observe(this, new Observer<List<Preference>>() {
            @Override
            public void onChanged(List<Preference> filteredPreferences) {
                // Update the RecyclerView with the filtered preferences data
                preferenceAdapter.setPreferences(filteredPreferences);
            }
        });

        // Get the selected genres, actors, and keywords from SharedPreferences
        List<String> selectedGenres = getSelectedGenresFromSharedPreferences();
        List<String> selectedActors = getSelectedActorsFromSharedPreferences();
        List<String> selectedKeywords = getSelectedKeywordsFromSharedPreferences();

        // Fetch and filter the movies based on the selected preferences
        preferenceViewModel.loadFilteredMovies();

        // Initialize the Hamburger Menu
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Handle Hamburger Menu item selections
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_searchmovies) {
                    openSearchMoviesActivity();
                } else if (itemId == R.id.menu_savedmovies) {
                    openSavedMoviesActivity();
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    // Helper method to retrieve selected genres from SharedPreferences
    private List<String> getSelectedGenresFromSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        return new ArrayList<>(preferences.getStringSet("selectedGenres", new HashSet<>()));
    }

    // Helper method to retrieve selected actors from SharedPreferences
    private List<String> getSelectedActorsFromSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        return new ArrayList<>(preferences.getStringSet("selectedActors", new HashSet<>()));
    }

    // Helper method to retrieve selected keywords from SharedPreferences
    private List<String> getSelectedKeywordsFromSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        return new ArrayList<>(preferences.getStringSet("selectedKeywords", new HashSet<>()));
    }

    // Method to open SearchMoviesActivity
    private void openSearchMoviesActivity() {
        startActivity(new Intent(PreferencesActivity.this, SearchMoviesActivity.class));
    }

    // Method to open SavedMoviesActivity
    private void openSavedMoviesActivity() {
        startActivity(new Intent(PreferencesActivity.this, SavedMoviesActivity.class));
    }
}*/