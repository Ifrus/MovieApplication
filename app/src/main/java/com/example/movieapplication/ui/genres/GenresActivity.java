package com.example.movieapplication.ui.genres;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapplication.R;

import java.util.List;
import java.util.Set;

public class GenresActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GenreAdapter genreAdapter;
    private GenreViewModel genreViewModel;
    private Button btnSaveGenres;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_genres);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        genreAdapter = new GenreAdapter();
        recyclerView.setAdapter(genreAdapter);

        btnSaveGenres = findViewById(R.id.btnSaveGenres);
        btnSaveGenres.setEnabled(false);

        genreAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                updateSaveButtonEnabled();
            }
        });

        genreViewModel = new ViewModelProvider(this).get(GenreViewModel.class);
        genreViewModel.getGenres().observe(this, new Observer<List<Genre>>() {
            @Override
            public void onChanged(List<Genre> genres) {
                if (genres != null) {
                    genreAdapter.setGenres(genres);
                }
            }
        });
        btnSaveGenres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSelectedGenres();
            }
        });
    }

    private void showPreferencesSavedPopup() {
        Toast.makeText(this, "Your preferences are saved!", Toast.LENGTH_SHORT).show();}

    private void updateSaveButtonEnabled() {
        Set<String> selectedGenres = genreAdapter.getSelectedGenres();
        btnSaveGenres.setEnabled(!selectedGenres.isEmpty());
    }

    private void saveSelectedGenres() {
        Set<String> selectedGenres = genreAdapter.getSelectedGenres();
        // Save the selected genres in SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("selectedGenres", selectedGenres);
        editor.apply();
        showPreferencesSavedPopup();

        // Finish the activity and return to the previous screen
        finish();
    }
}

