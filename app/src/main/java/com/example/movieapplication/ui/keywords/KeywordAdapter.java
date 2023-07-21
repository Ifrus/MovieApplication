package com.example.movieapplication.ui.keywords;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapplication.R;

import java.util.ArrayList;
import java.util.List;

public class KeywordAdapter extends RecyclerView.Adapter<KeywordAdapter.KeywordViewHolder> {
    private List<String> keywordsList;

    public KeywordAdapter(List<String> keywordsList) {
        this.keywordsList = keywordsList;
    }

    @NonNull
    @Override
    public KeywordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keyword, parent, false);
        return new KeywordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KeywordViewHolder holder, int position) {
        String keyword = keywordsList.get(position);
        holder.keywordTextView.setText(keyword);
    }

    @Override
    public int getItemCount() {
        return keywordsList.size();
    }

    public class KeywordViewHolder extends RecyclerView.ViewHolder {
        TextView keywordTextView;

        public KeywordViewHolder(@NonNull View itemView) {
            super(itemView);
            keywordTextView = itemView.findViewById(R.id.keywordTextView);
        }
    }
}
