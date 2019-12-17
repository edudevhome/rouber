package com.devhome.eduardobastos.roouber;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;

public class Admob extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MobileAds.initialize(this, getString(R.string.IdApp));

    }
}
