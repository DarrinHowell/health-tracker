package com.example.health_tracker;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;


public class Stopwatch extends AppCompatActivity {
//    ViewPager viewPager;
    boolean isPaused;
    TextView textTimer;
    private Handler customHandler = new Handler();
    private long startTime=0L, timeInMilliseconds=0L, timeSwapBuff=0L, updateTime=0L;
    private int secs, mins, hours, milliseconds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        textTimer = findViewById(R.id.textTimer);
    }

    Runnable updateTimeThread = new Runnable() {
        @Override
        public void run() {
            setTime(true, startTime, (updateTime = timeSwapBuff + timeInMilliseconds),
                    timeSwapBuff, (timeInMilliseconds = SystemClock.uptimeMillis() - startTime));
            customHandler.postDelayed(this, 0);
        }
    };

    public void startTimer(View view) {
        startTime = SystemClock.uptimeMillis();

        if(isPaused) {
            isPaused = false;
        }

        customHandler.postDelayed(updateTimeThread, 0);
    }

    public void pauseTimer(View view) {
        isPaused = true;
        timeSwapBuff += timeInMilliseconds;
        customHandler.removeCallbacks(updateTimeThread);
    }

    public void resetTimer(View view) {
        // reset all values back to zero.
        if(isPaused) {
            setTime( false,0,0,0, 0);
            customHandler.removeCallbacks(updateTimeThread);
        }

    }

    public void onHomeNavClick(View view) {
        Intent navigateHome = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(navigateHome);
        setContentView(R.layout.activity_main);
    }

    public void setTime(boolean update, long updatedStartTime, long updatedUpdateTime, long updatedTimeSwapBuff, long updatedTimeInMilliseconds) {
        timeInMilliseconds = updatedTimeInMilliseconds;
        startTime = updatedStartTime;
        updateTime = updatedUpdateTime;
        timeSwapBuff = updatedTimeSwapBuff;

        if (update) {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
        }
        updateTime = timeSwapBuff + timeInMilliseconds;
        secs = (int) updateTime / 1000;
        mins = secs / 60;
        hours = mins / 60;
        secs %= 60;
        milliseconds = (int) (updateTime % 1000);
        textTimer.setText("" + String.format("%2d", hours) + ":" + String.format("%02d", mins) + ":" +
                String.format("%02d", secs) + ":" +
                String.format("%03d", milliseconds));
    }

}
