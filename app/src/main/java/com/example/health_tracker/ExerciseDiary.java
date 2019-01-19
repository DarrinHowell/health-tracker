package com.example.health_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// attribution to Jess Lovell for the tip on running our DB on the main UI thread.
public class ExerciseDiary extends AppCompatActivity {
    ExerciseDatabase db;
    ListView databaseListView;
    long lastExerciseRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_diary);

        db = Room.databaseBuilder(getApplicationContext(),
                ExerciseDatabase.class, "exercise-diary").allowMainThreadQueries().build();

        renderExerciseFromDB();

    }

    public void renderExerciseFromDB() {

        getExercisesFromServer();

        List<Exercise> exercises = db.exerciseDao().getAll();

        databaseListView = findViewById(R.id.exerciseList);

        ArrayAdapter<Exercise> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exercises);

        databaseListView.setAdapter(arrayAdapter);

    }

    // attribution for request pattern: https://developer.android.com/training/volley/simple
    public void getExercisesFromServer() {

        List<Exercise> localExerciseRecords;

        if(db.exerciseDao().getAll() != null) {
            localExerciseRecords = db.exerciseDao().getAll();
            lastExerciseRecord = localExerciseRecords.size();
        } else {
            lastExerciseRecord = 0;
        }




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

                        // from JSON
                        Gson gson = new Gson();
                        Exercise[] exerciseList = gson.fromJson(response, Exercise[].class);

                        List<Exercise> newExerciseEntries = new ArrayList<>();

                        for (int i = (int) lastExerciseRecord; i < exerciseList.length; i++) {
                            newExerciseEntries.add(exerciseList[i]);
                        }

                        for(Exercise exercise : newExerciseEntries) {
                            String title = exercise.title;
                            String quantity = exercise.quantity;
                            String description = exercise.description;
                            String timeStamp = exercise.timeStamp;

                            Exercise newExercise = new Exercise(title, quantity, description, timeStamp);

                            db.exerciseDao().insert(newExercise);

                        }

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

    // attribution for performing a post reqeust and sending a post.body: https://stackoverflow.com/questions/33573803/how-to-send-a-post-request-using-volley-with-string-body
    // saves new exercise entity to local db and sends a post body to server to be saved in server db
    public void postExercisesToLocalAndDeployedDBs(View view) {

        Exercise recordedExercise = buildExerciseFromFormInput();
        db.exerciseDao().insert(recordedExercise);

        Gson gson = new Gson();
        final String recordedExerciseStringified = gson.toJson(recordedExercise);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://health-tracker-backend-dbh.herokuapp.com/newExercise";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("VOLLEY", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return recordedExerciseStringified == null ? null : recordedExerciseStringified.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", recordedExerciseStringified, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        requestQueue.add(stringRequest);

        finish();
        startActivity(getIntent());
    }

    // gets input data from exercise diary form, returns exercise object
    public Exercise buildExerciseFromFormInput() {
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

        return recordedExercise;
    }

}
