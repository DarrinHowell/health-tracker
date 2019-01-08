package com.example.health_tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    public int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // keeps track of user's clicks on the main activity
    public void clickCount(View view) {
        counter++;
        TextView homeView = findViewById(R.id.counterText);
        homeView.setText("Keep clicking! Here is your click count: " + counter);

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
}
