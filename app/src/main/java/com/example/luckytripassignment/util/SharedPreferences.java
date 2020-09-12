package com.example.luckytripassignment.util;

import android.content.Context;

import com.example.luckytripassignment.data.Rooms;
import com.google.gson.Gson;

public class SharedPreferences {


    public static final String ROOMS = "Rooms";
    public static final String ROOM = "room";
    public static final String IS_ROOM_PRESSED = "isRoomPressed";
    public static final String IS_FIRST_LAUNCH = "isFirstLaunch";

    public static void saveRoomObject(Context context, Rooms.Room room) {

        android.content.SharedPreferences.Editor prefsEditor = context.getSharedPreferences(ROOMS, Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(room);
        prefsEditor.putString(ROOM, json);
        prefsEditor.apply();

    }

    public static Rooms.Room retrieveRoomObject(Context context) {

        Gson gson = new Gson();
        String json = context.getSharedPreferences(ROOMS, Context.MODE_PRIVATE).getString(ROOM, "");
        return gson.fromJson(json, Rooms.Room.class);

    }

    public static void setBooleanPrefs(Context context,String key, Boolean Value) {

        context.getSharedPreferences(ROOMS, Context.MODE_PRIVATE).edit().putBoolean(key, Value).apply();

    }

    public static Boolean getBooleanPrefs(Context context,String key, Boolean defaultValue) {

        return context.getSharedPreferences(ROOMS, Context.MODE_PRIVATE).getBoolean(key, defaultValue);

    }

}