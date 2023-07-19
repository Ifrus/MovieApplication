package com.example.movieapplication.ui.genres;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapplication.R;
import com.example.movieapplication.ui.SharedPreferencesHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenresActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GenreAdapter genreAdapter;

    private GenreViewModel genreViewModel;

    /*SharedPreferencesHelper<String> genreSharedPreferencesHelper = new SharedPreferencesHelper<>(this);
    Set<String> selectedGenres = new HashSet<>();
    GenreSharedPreferencesHelper.saveData("selected_genres", selectedGenres);*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_genres);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        genreAdapter = new GenreAdapter();
        recyclerView.setAdapter(genreAdapter);

        genreViewModel = new ViewModelProvider(this).get(GenreViewModel.class);
        genreViewModel.getGenres().observe(this, new Observer<List<Genre>>() {
            @Override
            public void onChanged(List<Genre> genres) {
                if (genres != null) {
                    genreAdapter.setGenres(genres);
                }
            }
        });

      /*  btnSaveGenres.setOnClickListener(new View.OnClickListener()){
            @Override
            public void onClick(View view){
                List<Genre> selectedGenres = genreAdapter.getSelectedGenres();
                genreViewModel.saveSelectedGenres(selectedGenres);
            }
        }*/
    }
}
