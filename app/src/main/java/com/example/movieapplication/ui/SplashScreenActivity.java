package com.example.movieapplication.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapplication.R;
import com.example.movieapplication.ui.on_boarding.OnBoardingActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private Handler handler;
    private Runnable runnable;
    private static final long DELAY = 2000L;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splashscreen);
        initHandlerToOpenNextActivity();
    }

    private void initHandlerToOpenNextActivity(){
        handler = new Handler(Looper.getMainLooper());
        runnable = this::openNextScreen;
        handler.postDelayed(runnable, DELAY);
    }

    private void openNextScreen(){
        OnBoardingActivity.open(this);
        finish();
    }

    @Override
    protected void onDestroy(){
        removeHandler();
        super.onDestroy();
    }

    @Override
    public void onBackPressed(){
        removeHandler();
        super.onBackPressed();
    }

    private void removeHandler(){
        if(handler!=null && runnable != null)
        {
            handler.removeCallbacks(runnable);
            runnable = null;
            handler = null;
        }
    }
}

