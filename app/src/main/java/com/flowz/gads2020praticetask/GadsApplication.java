package com.flowz.gads2020praticetask;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

public class GadsApplication extends Application {

        @Override public void onCreate() {
            super.onCreate();
            AndroidThreeTen.init(this);
        }
}
