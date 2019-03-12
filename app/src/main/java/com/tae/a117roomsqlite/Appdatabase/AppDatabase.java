package com.tae.a117roomsqlite.Appdatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.tae.a117roomsqlite.Dao.UserDao;
import com.tae.a117roomsqlite.Entity.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
   public abstract UserDao userDao();
}