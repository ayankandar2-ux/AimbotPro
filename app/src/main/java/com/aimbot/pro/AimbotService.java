package com.aimbot.pro;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;

public class AimbotService extends Service {
    static {
        System.loadLibrary("aimbot");
    }

    private native void nativeInit();
    private native void nativeStart();
    private native void nativeStop();

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        startForeground(1, buildNotification());
        nativeInit();
        nativeStart();
        Toast.makeText(this, "Aimbot active", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        nativeStop();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) { return null; }

    private Notification buildNotification() {
        return new NotificationCompat.Builder(this, "aimbot_channel")
                .setContentTitle("Aimbot Pro")
                .setContentText("Active")
                .setSmallIcon(android.R.drawable.ic_menu_camera)
                .setOngoing(true)
                .build();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel("aimbot_channel",
                    "Aimbot Service", NotificationManager.IMPORTANCE_LOW);
            getSystemService(NotificationManager.class).createNotificationChannel(channel);
        }
    }
}
