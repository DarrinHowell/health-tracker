package com.example.health_tracker;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

public class NotificationInitializor extends AppCompatActivity {
    private static final String CHANNEL_ID = "myID";
    private int notificationId = 0;
    public MyNotificationPublisher NotificationPublisher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_initializor);

        createNotificationChannel();
    }

    // pattern adapted from: https://stackoverflow.com/questions/36902667/how-to-schedule-notification-in-android
    public void onClickScheduleNotification(View view) {

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_action_name_notification)
                .setContentTitle("Health.Notify.Hydrate")
                .setContentText("Another 2 hours have passed. Replenish your fluids.")
                .setStyle( new NotificationCompat.BigTextStyle()
                    .bigText("2 hours have passed. Get those fluids."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        Intent intent = new Intent(this, MyNotificationPublisher.class);
        intent.putExtra("notification", mBuilder.build());
        intent.putExtra("notification_id", notificationId++);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 5000, pendingIntent);


    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Make it Rain";
            String description = "Time to Hydrate";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
