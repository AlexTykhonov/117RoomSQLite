package com.tae.a117roomsqlite.repository;

import android.arch.lifecycle.LiveData;

import com.tae.a117roomsqlite.DATA.User;
import com.tae.a117roomsqlite.DATA.UserDao;

import java.util.List;

import javax.inject.Inject;

public class UserDataSource {

    @Inject
    UserDao userDao;

    public UserDataSource(UserDao userDao) {
        this.userDao = userDao;
    }

    public LiveData<User> findById (long id) {
        return userDao.getById(id);
    }

    public LiveData<List<User>> findAll () {
        return userDao.getAll();
    }

    public void save (User user) {
        userDao.insert(user);
        System.out.println(user + "                                        from method SAVE      +");
    }
}
