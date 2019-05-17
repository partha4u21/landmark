package com.landmark.assignment.services;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.landmark.assignment.R;
import com.landmark.assignment.helpers.ConfigHelper;

public class DownloadConfigService extends IntentService {

    public static boolean isIntentServiceRunning = false;
    private Messenger messenger;

    public DownloadConfigService() {
        super("Download service");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DownloadConfigService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            startForegroundService();
        else
            startForeground(1, new Notification());
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void startForegroundService() {
        String NOTIFICATION_CHANNEL_ID = "com.landmark";
        String channelName = "LANDMARK";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setContentTitle("App is running in background")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(2, notification);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        isIntentServiceRunning = true;
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            messenger = (Messenger) bundle.get("messenger");

        }
        ConfigHelper.downloadConfig(this,messenger);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isIntentServiceRunning = false;
    }
}
