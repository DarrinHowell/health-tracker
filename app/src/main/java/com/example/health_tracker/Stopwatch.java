package com.example.health_tracker;

import android.content.Intent;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class Stopwatch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
    }

    public void toggleStartStop(View view) {
        TextView homeView = findViewById(R.id.stopWatch_start);
        CharSequence buttonText = homeView.getText();
        if(buttonText.equals("Start")) {
            homeView.setText("Pause");
        } else {
            homeView.setText("Start");
        }
    }

    public void onHomeNavClick(View view) {
        Intent navigateHome = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(navigateHome);
        setContentView(R.layout.activity_main);
    }

//    @Override
//    public void run() {
//        android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
//    }
//
//
//    class StopWatchManager extends Thread {
//        // instance variable for time when thread is started.
//
//        public StopWatchManager(Date now) {
//
//        }
//
//        @Override
//        public void run() {
//            // we will want a function that captures the current time at which the thread is started
//            // this function will also keep track of currentTimeMillis and subtract the current
//            // time from the start time, giving us the time elapsed since starting our thread.
//
//            // we will call this function every time start is pressed
//
//            // returns the current time in milliseconds
////            System.out.print("Current Time in milliseconds = ");
////            System.out.println(System.currentTimeMillis());
//        }
//    }
}
