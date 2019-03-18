package com.tae.a117roomsqlite.App;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.tae.a117roomsqlite.repository.AppDatabase;

import javax.inject.Inject;

public class App extends Application {
 
    public static App instance;

    @Inject
     AppDatabase database;
 
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "books")
        .allowMainThreadQueries().build();
    }
 
    public static App getInstance() {
        return instance;
    }
 
    public AppDatabase getDatabase() {
        return database;
    }
}