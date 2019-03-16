package com.tae.a117roomsqlite.DI;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.tae.a117roomsqlite.App.App;
import com.tae.a117roomsqlite.DATA.UserDao;
import com.tae.a117roomsqlite.repository.AppDatabase;
import com.tae.a117roomsqlite.repository.UserDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    private AppDatabase appDatabase;

    public RoomModule(Application application) {
        this.appDatabase = Room.databaseBuilder(application,AppDatabase.class,"user").build();
    }

    @Singleton
    @Provides
    AppDatabase providesAppDataBase () {
        return appDatabase;
    }

    @Singleton
    @Provides
    UserDao providesUserDao (AppDatabase appDatabase) {
        return appDatabase.userDao();
    }

    @Singleton
    @Provides
    UserDataSource providesUserDataSource(UserDao userDao) {
        return new UserDataSource(userDao);
    }

}
