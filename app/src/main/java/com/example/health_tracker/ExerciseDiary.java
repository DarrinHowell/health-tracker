package com.example.health_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

        renderExerciseToDB();

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

    public void recordExercise(View view){
        final EditText titleField = (EditText) findViewById(R.id.exerciseTitle);
        String title = titleField.getText().toString();

        final EditText quantityField = (EditText) findViewById(R.id.exerciseQuantity);
        String quantity = quantityField.getText().toString();

        final EditText descriptionField = (EditText) findViewById(R.id.exerciseDescription);
        String description = descriptionField.getText().toString();

        Date date = new Date();
        String dateFormatStringified = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(dateFormatStringified);
        String formattedDate = dateFormat.format(date);

        Exercise recordedExercise = new Exercise(title, quantity, description, formattedDate);

        db.exerciseDao().insert(recordedExercise);

        finish();
        startActivity(getIntent());
    }

}
