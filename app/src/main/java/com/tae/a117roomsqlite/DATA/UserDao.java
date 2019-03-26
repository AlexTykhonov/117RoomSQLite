package com.tae.a117roomsqlite.DATA;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.tae.a117roomsqlite.DATA.User;

import java.util.List;

@Dao
public interface UserDao {
 
   @Query("SELECT * FROM users")
   List<User> getAll();
 
   @Query("SELECT * FROM users WHERE id = :id")
   User getById(long id);
 
   @Insert
   void insert(User user);
 
   @Update
   void update(User user);
 
   @Delete
   void delete(User user);
 
}