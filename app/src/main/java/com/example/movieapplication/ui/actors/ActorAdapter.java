package com.example.movieapplication.ui.actors;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ActorViewHolder> {
    private List<Actor> actors = new ArrayList<>();
    private Set<String> selectedActors = new HashSet<>();

    public void setActors(List<Actor> actors) {
        this.actors = actors;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actor, parent, false);
        return new ActorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder holder, int position) {
        Actor actor = actors.get(position);
        holder.bind(actor);
    }

    @Override
    public int getItemCount() {
        return actors.size();
    }

    public Set<String> getSelectedActors() {
        return selectedActors;
    }

    public static void toggleActorSelection(Actor actor, Set<String> selectedActors) {
        actor.setSelected(!actor.isSelected());
        if (actor.isSelected()) {
            selectedActors.add(actor.getName());
        } else {
            selectedActors.remove(actor.getName());
        }
    }
    class ActorViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewActorName;
        private final ImageView actorsImageView;

        public ActorViewHolder(@NonNull View itemView) {
            super(itemView);
            actorsImageView = itemView.findViewById(R.id.actorsImageView);
            textViewActorName = itemView.findViewById(R.id.textViewActorName);

           textViewActorName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Actor actor = actors.get(position);
                        toggleActorSelection(actor, selectedActors);
                        notifyDataSetChanged(); // Refresh the list to update selection colors
                    }
                }
            });
        }

        public void bind(Actor actor) {
            textViewActorName.setText(actor.getName());

            // Change the text color based on the selection status
            if (actor.isSelected()) {
                textViewActorName.setTextColor(Color.GREEN);
            } else {
                textViewActorName.setTextColor(Color.BLACK);
            }

            // Load the actor's image using Picasso or your preferred image loading library
            String profile_path = actor.getImage();
            String imageUrl = "https://image.tmdb.org/t/p/w200" + profile_path;
            Picasso.get().load(imageUrl).into(actorsImageView);
        }
    }
}

