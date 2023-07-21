package com.example.movieapplication.ui.preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
    protected void onCreate(Bundle savedInstanceState) {
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
            public void onChanged(List<Preference> filteredMovies) {
                // Update the RecyclerView with the filtered movies data
                preferenceAdapter.setPreferences(filteredMovies);
            }
        });

        // Get the selected genres, actors, and keywords from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        List<String> selectedGenres = new ArrayList<>(sharedPreferences.getStringSet("selectedGenres", new HashSet<>()));
        List<String> selectedActors = new ArrayList<>(sharedPreferences.getStringSet("selectedActors", new HashSet<>()));
        List<String> selectedKeywords = new ArrayList<>(sharedPreferences.getStringSet("selectedKeywords", new HashSet<>()));

        // Fetch and filter the movies based on the selected preferences
        preferenceViewModel.loadFilteredMovies(selectedGenres, selectedActors, selectedKeywords);

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
    // Method to open SearchMoviesActivity
    private void openSearchMoviesActivity() {
        startActivity(new Intent(PreferencesActivity.this, SearchMoviesActivity.class));
    }

    // Method to open SavedMoviesActivity
    private void openSavedMoviesActivity() {
        startActivity(new Intent(PreferencesActivity.this, SavedMoviesActivity.class));
    }
}
