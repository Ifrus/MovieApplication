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
        private List<Keyword> keywordsList = new ArrayList<>();
        private List<Keyword> selectedKeywords = new ArrayList<>();

        public List<Keyword> getSelectedKeywords() {
            return selectedKeywords;
        }

        public void setKeywordsList(List<Keyword> keywordsList) {
            this.keywordsList = keywordsList;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public KeywordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keyword, parent, false);
            return new KeywordViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull KeywordViewHolder holder, int position) {
            Keyword keyword = keywordsList.get(position);
            holder.bind(keyword);
        }

        @Override
        public int getItemCount() {
            return keywordsList.size();
        }

class KeywordViewHolder extends RecyclerView.ViewHolder {
    private TextView tvKeywordName;

    KeywordViewHolder(@NonNull View itemView) {
        super(itemView);
        tvKeywordName = itemView.findViewById(R.id.tvKeywordName);

        itemView.setOnClickListener(v -> {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Keyword selectedKeyword = keywordsList.get(position);
                if (selectedKeywords.contains(selectedKeyword)) {
                    selectedKeywords.remove(selectedKeyword);
                } else {
                    selectedKeywords.add(selectedKeyword);
                }
                notifyDataSetChanged();
            }
        });
    }

    void bind(Keyword keyword) {
        //  tvKeywordName.setText(keyword.getName());
        if (selectedKeywords.contains(keyword)) {
            itemView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.selected_keyword_bg));
        } else {
            itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }
}
}
