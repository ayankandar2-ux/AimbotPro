package com.aimbot.pro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_OVERLAY = 100;
    private static final int REQUEST_SCREEN = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this)) {
            startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName())), REQUEST_OVERLAY);
        }

        Button btn = findViewById(R.id.btn_start);
        btn.setText("Start Aimbot");
        btn.setOnClickListener(v -> {
            startService(new Intent(this, AimbotService.class));
            Toast.makeText(this, "Aimbot service started", Toast.LENGTH_SHORT).show();
            // Request screen capture for future ESP use
            MediaProjectionManager mp = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
            startActivityForResult(mp.createScreenCaptureIntent(), REQUEST_SCREEN);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Screen capture result can be saved for later
    }
}
