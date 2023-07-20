package com.example.movieapplication.ui.genres;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapplication.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {
    private List<Genre> genres = new ArrayList<>();
    private Set<String> selectedGenres = new HashSet<>();

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genre, parent, false);
        return new GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        Genre genre = genres.get(position);
        holder.bind(genre);
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public Set<String> getSelectedGenres() {
        return selectedGenres;
    }

    public static void toggleGenreSelection(Genre genre, Set<String> selectedGenres) {
        genre.setSelected(!genre.isSelected());
        if (genre.isSelected()) {
            selectedGenres.add(genre.getName());
        } else {
            selectedGenres.remove(genre.getName());
        }
    }
    class GenreViewHolder extends RecyclerView.ViewHolder {
        private final TextView genreNameTextView;

        public GenreViewHolder(@NonNull View itemView) {
            super(itemView);
            genreNameTextView = itemView.findViewById(R.id.genreNameTextView);
            genreNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Genre genre = genres.get(position);
                        toggleGenreSelection(genre, selectedGenres);
                        notifyDataSetChanged(); // Refresh the list to update selection colors
                    }
                }
            });
        }
        public void bind(Genre genre) {
            genreNameTextView.setText(genre.getName());
            // Change the text color based on the selection status
            if (genre.isSelected()) {
                genreNameTextView.setTextColor(Color.GREEN);
            } else {
                genreNameTextView.setTextColor(Color.BLACK);
            }
        }
    }
}
