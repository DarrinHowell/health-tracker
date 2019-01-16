package com.example.health_tracker;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import java.sql.Timestamp;

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

    protected void onHydratehNavClick(View view) {
        Intent navigateToHydrate = new Intent(getApplicationContext(), NotificationInitializor.class);
        startActivity(navigateToHydrate);
        setContentView(R.layout.activity_notification_initializor);

    }

    public void onExerciseDiaryNavClick(View view) {
        Intent navigateToDiary = new Intent(getApplicationContext(), ExerciseDiary.class);
        startActivity(navigateToDiary);
        setContentView(R.layout.activity_exercise_diary);
    }


    // attribution for pattern of adding to the db via dao: https://medium.freecodecamp.org/room-sqlite-beginner-tutorial-2e725e47bfab

}
