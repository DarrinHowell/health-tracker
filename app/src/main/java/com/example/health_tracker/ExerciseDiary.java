package com.example.health_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// attribution to Jess Lovell for the tip on running our DB on the main UI thread.
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
        getExercisesFromServer();

    }

    public void renderExerciseToDB() {

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

    public void getExercisesFromServer() {
//        final TextView mTextView = (TextView) findViewById(R.id.text);

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://health-tracker-backend-dbh.herokuapp.com/exercises";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        System.out.println("Response is: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!" + error);
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}
