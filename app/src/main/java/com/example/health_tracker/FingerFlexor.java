package com.example.health_tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FingerFlexor extends AppCompatActivity {
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_flexor);
    }

    // keeps track of user's clicks on the main activity
    public void clickCount(View view) {
        counter++;
        TextView homeView = findViewById(R.id.fingerFlexCounter);
        String counterStringified = Integer.toString(counter);
        homeView.setText(counterStringified);
    }
}
