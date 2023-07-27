package com.example.movieapplication.ui.actors;

import static android.content.ContentValues.TAG;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapplication.R;
import com.google.gson.Gson;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActorsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ActorAdapter actorAdapter;

    private ActorViewModel actorViewModel;

    private Button btnSaveActors;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actors);

        recyclerView = findViewById(R.id.recyclerViewActors);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        actorAdapter = new ActorAdapter();
        recyclerView.setAdapter(actorAdapter);

        btnSaveActors = findViewById(R.id.btnSaveActors);
        btnSaveActors.setEnabled(false);

        actorAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                updateSaveButtonEnabled();
            }
        });


        actorViewModel = new ViewModelProvider(this).get(ActorViewModel.class);
        actorViewModel.getActors().observe(this, new Observer<List<Actor>>() {
            @Override
            public void onChanged(List<Actor> actors) {
                if (actors != null) {
                    actorAdapter.setActors(actors);
                }
            }
        });
        btnSaveActors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSelectedActors();
            }
        });
    }

    private void showPreferencesSavedPopup() {
        Toast.makeText(this, "Your preferences are saved!", Toast.LENGTH_SHORT).show();}


    private void updateSaveButtonEnabled() {
        Set<Integer> selectedActors = actorAdapter.getSelectedActors();
        btnSaveActors.setEnabled(!selectedActors.isEmpty());
    }

    private void saveSelectedActors() {
        Set<Integer> selectedActors = actorAdapter.getSelectedActors();

        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String selectedActorsJson = gson.toJson(selectedActors);
        Log.d(TAG, "saveSelectedActors: " + selectedActorsJson);

        editor.putString("selectedActors", selectedActorsJson);
        editor.apply();
        finish();
    }
}

