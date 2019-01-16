package com.example.health_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExerciseDiary extends AppCompatActivity {
    ExerciseDatabase db;
    ListView databaseListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_diary);



        db = Room.databaseBuilder(getApplicationContext(),
                ExerciseDatabase.class, "exercise-diary").allowMainThreadQueries().build();

        addExerciseToDB();

        renderExerciseToDB();

    }

    // test method -- adding an exercise to the database
    public void addExerciseToDB() {

        // attribution to date formatter: https://dzone.com/articles/getting-current-date-time-in-java
        // create and format date object
        Date date = new Date();
        String dateFormatStringified = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(dateFormatStringified);
        String formattedDate = dateFormat.format(date);

        // create new exercise
        Exercise testExercise = new Exercise("Pull Ups", "30", "strict pull ups", formattedDate);

        // add that to the DB
        db.exerciseDao().insert(testExercise);

    }

    public void renderExerciseToDB() {

        // retrieve exercise
        // find correct listView
        // set View Data into listView

        List<Exercise> exercises = db.exerciseDao().getAll();

        databaseListView = findViewById(R.id.exerciseList);

        ArrayAdapter<Exercise> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exercises);

        databaseListView.setAdapter(arrayAdapter);

    }

}
