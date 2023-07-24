package com.example.movieapplication.ui.preferences;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;
public class PreferenceAdapter extends RecyclerView.Adapter<PreferenceAdapter.PreferenceViewHolder> {
    private List<Preference> preferences;

    public void setPreferences(List<Preference> preferences) {
        this.preferences = preferences;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PreferenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_preference, parent, false);
        return new PreferenceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PreferenceViewHolder holder, int position) {
        Preference preference = preferences.get(position);
        holder.bind(preference);
        Log.d("PreferenceAdapter", "Movie Title: " + preference.getTitle());
        Log.d("PreferenceAdapter", "Movie Overview: " + preference.getMovieOverview());
        Log.d("PreferenceAdapter", "Poster URL: " + preference.getPosterUrl());
    }

    @Override
    public int getItemCount() {
        return preferences != null ? preferences.size() : 0;
    }

    static class PreferenceViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView overviewTextView;
        private final ImageView posterImageView;

        public PreferenceViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            overviewTextView = itemView.findViewById(R.id.overviewTextView);
            posterImageView = itemView.findViewById(R.id.posterImageView);
        }

        public void bind(Preference preference) {
            titleTextView.setText(preference.getMovieTitle());
            overviewTextView.setText(preference.getMovieOverview());

            // Load the preference's image using Picasso or your preferred image loading library
            String poster_path = preference.getPosterUrl();
            String imageUrl = "https://image.tmdb.org/t/p/w200" + poster_path;
            Picasso.get().load(imageUrl).into(posterImageView);
        }
    }
}