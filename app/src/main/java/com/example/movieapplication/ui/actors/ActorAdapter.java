package com.example.movieapplication.ui.actors;

import android.annotation.SuppressLint;
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
import java.util.List;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ActorViewHolder>{
    private List<Actor> actors = new ArrayList<>();

    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actor, parent, false);
        return new ActorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder holder, int position) {
        Actor actor = actors.get(position);
        holder.textViewActorName.setText(actor.getName());

        String profile_path = actor.getImage();
        String imageUrl = "https://image.tmdb.org/t/p/w200"+ profile_path;
        Picasso.get().load(imageUrl).into(holder.actorsImageView);
    }

    @Override
    public int getItemCount() {
        return actors.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setActors(List<Actor> actors) {
        this.actors = actors;
        notifyDataSetChanged();
    }


    static class ActorViewHolder extends RecyclerView.ViewHolder {

        TextView textViewActorName;
        ImageView actorsImageView;

        public ActorViewHolder(@NonNull View itemView) {
            super(itemView);
            actorsImageView = itemView.findViewById(R.id.actorsImageView);
            textViewActorName = itemView.findViewById(R.id.textViewActorName);
        }
    }
}
