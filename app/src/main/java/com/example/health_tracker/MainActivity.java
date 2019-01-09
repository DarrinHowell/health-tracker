package com.example.health_tracker;

import android.content.Intent;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void onFingerFlexorNavClick(View view) {
        Intent navigateToFingerFlexor = new Intent(getApplicationContext(), FingerFlexor.class);
        startActivity(navigateToFingerFlexor);
        setContentView(R.layout.activity_finger_flexor);
    }
    // attribution -- general intent / view pattern adapted from StackOverflow
    // https://stackoverflow.com/questions/17526533/moving-from-one-activity-to-another-activity-in-android


    protected void onStopwatchNavClick(View view) {
        Intent navigateToStopWatch = new Intent(getApplicationContext(), Stopwatch.class);
        startActivity(navigateToStopWatch);
        setContentView(R.layout.activity_stopwatch);
    }


}
