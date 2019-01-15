package com.example.health_tracker;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyNotificationPublisher extends BroadcastReceiver {

    // attribution: pattern taken from Michelle's in class demo.

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = intent.getParcelableExtra("notification");
        int notificationId = intent.getIntExtra("notification_id", 0);
        notificationManager.notify(notificationId, notification);
    }

}
