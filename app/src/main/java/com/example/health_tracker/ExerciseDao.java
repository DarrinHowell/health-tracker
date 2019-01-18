package com.example.health_tracker;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ExerciseDao {
    @Query("SELECT * FROM exercise")
    List<Exercise> getAll();

    @Insert
    void insert(Exercise exercise);

    @Insert
    void insertAll(List<Exercise> exercises);

    @Delete
    void delete(Exercise exercise);
}
