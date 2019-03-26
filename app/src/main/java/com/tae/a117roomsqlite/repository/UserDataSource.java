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

    public User findById (Long id) {
        return userDao.getById(id);
    }

    public List<User> findAll () {
        return userDao.getAll();
    }

    public void save (User user) {
        userDao.insert(user);
        System.out.println(user + "                                        from method SAVE      +");
    }

    public void update (User user) {
        userDao.update(user);
    }

    public void delete (User user) {
        userDao.delete(user);
    }
}
