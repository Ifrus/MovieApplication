package com.example.movieapplication.ui.movie_details;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.movieapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {
    private MovieDetailViewModel movieDetailViewModel;
    private int movieId;
    private TextView titleYearTextView;
    private ImageView posterImageView;
    private TextView overviewTextView;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_movie_details);

        movieDetailViewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
        movieId = getIntent().getIntExtra("movie_id", -1);

        titleYearTextView = findViewById(R.id.titleYearTextView);
        posterImageView = findViewById(R.id.posterImageView);
        overviewTextView = findViewById(R.id.overviewTextView);
        videoView = findViewById(R.id.videoView);

        Intent intent = getIntent();
        if (intent != null) {
            movieId = intent.getIntExtra("movie_id", -1);
            String movieTitle = intent.getStringExtra("movieTitle");
            String posterUrl = intent.getStringExtra("posterUrl");
            String overview = intent.getStringExtra("overview");
            String releaseDate = intent.getStringExtra("releaseDate");
            ArrayList<String> videoUrls = intent.getStringArrayListExtra("videoUrls");

            titleYearTextView.setText(movieTitle + " (" + releaseDate + ")");
            overviewTextView.setText(overview);

            String imageUrl = "https://image.tmdb.org/t/p/w200" + posterUrl;
            Picasso.get().load(imageUrl).into(posterImageView);

            if (videoUrls != null && videoUrls.size() > 0) {
                String firstVideoUrl = videoUrls.get(0);
                initializeVideoPlayer(firstVideoUrl);
            }
        }
    }

    private void initializeVideoPlayer(String videoUrl) {
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.e("VideoPlayer", "Error occurred while playing video");
                // Handle the error (e.g., show an error message)
                return false;
            }
        });

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoPath(videoUrl);
        videoView.start();
    }
}
