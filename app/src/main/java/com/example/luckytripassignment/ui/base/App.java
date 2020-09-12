package com.example.luckytripassignment.ui.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.example.luckytripassignment.util.SharedPreferences;

public class App extends Application {

    //Global context
    @SuppressLint("StaticFieldLeak")
    public static Context mApp = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;

        //First time app launch boolean value
        SharedPreferences.setBooleanPrefs(this,SharedPreferences.IS_FIRST_LAUNCH,true);

    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

}
