package com.aimbot.pro;

import android.app.Application;

public class AimbotApplication extends Application {
    private static AimbotApplication instance;
    @Override public void onCreate() { super.onCreate(); instance = this; }
    public static AimbotApplication getInstance() { return instance; }
}
