package com.tae.Appdatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.tae.a117roomsqlite.Dao.EmployeeDao;
import com.tae.a117roomsqlite.Entity.Employee;

@Database(entities = {Employee.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
   public abstract EmployeeDao employeeDao();
}