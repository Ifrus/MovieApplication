package com.example.movieapplication.ui.actors;

import android.os.Bundle;
import android.support.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapplication.R;

import java.util.List;

public class ActorsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ActorAdapter actorAdapter;

    private ActorViewModel actorViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actors);

        recyclerView = findViewById(R.id.recyclerViewActors);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        actorAdapter = new ActorAdapter();
        recyclerView.setAdapter(actorAdapter);

        actorViewModel = new ViewModelProvider(this).get(ActorViewModel.class);
        actorViewModel.getActors().observe(this, new Observer<List<Actor>>() {
            @Override
            public void onChanged(List<Actor> actors) {
                if (actors != null) {
                    actorAdapter.setActors(actors);
                }
            }
        });
    }
}
