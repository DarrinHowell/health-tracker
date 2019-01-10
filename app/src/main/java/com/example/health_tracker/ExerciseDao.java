package com.example.health_tracker;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ExerciseDao {
    @Query("SELECT * FROM exercise")
    List<Exercise> getAll();

    @Query("SELECT * FROM exercise WHERE eid IN (:exerciseIds)")
    List<Exercise> loadAllByIds(int[] exerciseIds);

    @Query("SELECT * FROM exercise WHERE title IN (:title)")
    String findByTitle (String title);

    @Insert
    void insertSingleExercise(Exercise exercise);

    @Delete
    void delete(Exercise exercise);

}
