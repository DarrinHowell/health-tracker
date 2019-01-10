package com.example.health_tracker;

import android.os.health.TimerStat;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


// attribution for entity pattern: https://developer.android.com/training/data-storage/room/
@Entity
public class Exercise {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "quantity")
    public int quantity;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "time_stamp")
    public Timestamp timeStamp;

    public Exercise() {}


    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getDescription() {
        return this.description;
    }

    public Timestamp getTimeStamp() {
        return this.timeStamp;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }




}

