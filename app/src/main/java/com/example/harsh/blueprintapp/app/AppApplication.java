package com.example.harsh.blueprintapp.app;

import android.app.Application;


public class AppApplication extends Application {

    public static final String TAG = AppApplication.class.getSimpleName();

    private static AppApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    public static AppApplication getInstance() {
        return instance;
    }
}