package com.example.movieapplication.ui.preferences;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapplication.R;
import com.example.movieapplication.ui.movie_details.MovieDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;
public class PreferenceAdapter extends RecyclerView.Adapter<PreferenceAdapter.PreferenceViewHolder> {
    private List<Preference> preferences;

    private OnItemClickListener onItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(Preference preference);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MovieDetailsActivity.class);
                intent.putExtra("movieTitle", preference.getTitle());
                intent.putExtra("posterUrl", preference.getPosterUrl());
                intent.putExtra("overview", preference.getMovieOverview());
                intent.putExtra("releaseDate", preference.getYear());
                v.getContext().startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(preference);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return preferences != null ? preferences.size() : 0;
    }

    static class PreferenceViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleYearTextView;
        private final TextView overviewTextView;
        private final ImageView posterImageView;
        private final ImageButton addToFavoritesButton;
        private final Button addToWatchedButton;



        public PreferenceViewHolder(@NonNull View itemView) {
            super(itemView);
            titleYearTextView = itemView.findViewById(R.id.titleYearTextView);
            overviewTextView = itemView.findViewById(R.id.overviewTextView);
            posterImageView = itemView.findViewById(R.id.posterImageView);
            addToFavoritesButton = itemView.findViewById(R.id.addToFavoritesButton);
            addToWatchedButton = itemView.findViewById(R.id.addToWatchedButton);

            addToFavoritesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {

                        listener.onFavoriteButtonClick(position);
                    }
                }
            });
        }

        public interface OnFavoriteButtonClickListener {
            void onFavoriteButtonClick(int position);
        }

        private OnFavoriteButtonClickListener listener;

        public void setOnFavoriteButtonClickListener(OnFavoriteButtonClickListener listener) {
            this.listener = listener;
        }

        public void bind(Preference preference) {
            titleYearTextView.setText(preference.getTitle() + " (" + preference.getYear() + ")");
            overviewTextView.setText(preference.getMovieOverview());

            // Load the preference's image using Picasso or your preferred image loading library
            String poster_path = preference.getPosterUrl();
            String imageUrl = "https://image.tmdb.org/t/p/w200" + poster_path;
            Picasso.get().load(imageUrl).into(posterImageView);

            if (preference.isFavorite()) {
                addToFavoritesButton.setImageResource(R.drawable.ic_heart_filled);
            } else {
                addToFavoritesButton.setImageResource(R.drawable.ic_heart_empty);
            }


            addToFavoritesButton.setOnClickListener(v -> {
                preference.setFavorite(!preference.isFavorite());
                if (preference.isFavorite()) {
                    addToFavoritesButton.setImageResource(R.drawable.ic_heart_filled);
                } else {
                    addToFavoritesButton.setImageResource(R.drawable.ic_heart_empty);
                }
            });

            addToWatchedButton.setOnClickListener(v -> {
            });
        }
    }
}