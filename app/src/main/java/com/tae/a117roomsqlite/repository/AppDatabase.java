package com.tae.a117roomsqlite.repository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.tae.a117roomsqlite.DATA.UserDao;
import com.tae.a117roomsqlite.DATA.User;

   @Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
   public abstract UserDao userDao();
}