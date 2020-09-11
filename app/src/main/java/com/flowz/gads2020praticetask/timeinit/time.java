package com.flowz.gads2020praticetask.timeinit;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

public class time extends Application {

        @Override public void onCreate() {
            super.onCreate();
            AndroidThreeTen.init(this);
        }
}
