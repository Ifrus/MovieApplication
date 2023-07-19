package com.example.movieapplication.ui.keywords;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KeywordResponse {
    @SerializedName("keywords")
    private List<Keyword> keywords;

    public List<Keyword> getKeywords() {
        return keywords;
    }
}
