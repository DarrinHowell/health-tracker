package com.example.health_tracker;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "quantity")
    public String quantity;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "time_stamp")
    public String timeStamp;

    public Exercise() {}

    @Ignore
    public Exercise(String title, String quantity, String description, String timeStamp) {
        this.title = title;
        this.quantity = quantity;
        this.description = description;
        this.timeStamp = timeStamp;
    }


    public String toString() {
        return "Title: " + this.title + ". Quantity: " + this.quantity + ". Description: " +
                description + "Date: " + this.timeStamp;
    }

}
