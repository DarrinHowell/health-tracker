package com.example.health_tracker;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NotificationInitializor extends AppCompatActivity {
    private static final String CHANNEL_ID = "myID";
    private int notificationId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_initializor);
    }

    public void onClickScheduleNotification(View view) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_action_name_notification)
                .setContentTitle("Health.Notify.Hydrate")
                .setContentText("Another 2 hours have passed. Replenish your fluids.")
                .setStyle( new NotificationCompat.BigTextStyle()
                    .bigText("2 hours have passed. Get those fluids."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, mBuilder.build());
    }
}
