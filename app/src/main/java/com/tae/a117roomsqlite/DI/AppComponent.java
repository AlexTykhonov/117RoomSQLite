package com.tae.a117roomsqlite.DI;

import android.app.Application;

import com.tae.a117roomsqlite.DATA.UserDao;
import com.tae.a117roomsqlite.repository.AppDatabase;
import com.tae.a117roomsqlite.repository.UserDataSource;
import com.tae.a117roomsqlite.view.MainActivity;
import com.tae.a117roomsqlite.view.UserActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (dependencies = {}, modules = {AppModule.class,RoomModule.class})
public interface AppComponent {

void inject (MainActivity mainActivity);
void inject (UserActivity userActivity);
   AppDatabase providesAppDataBase ();
    UserDao providesUserDao();
    UserDataSource providesUserDataSource();
    Application providesApplication();
}
