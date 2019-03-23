package com.tae.a117roomsqlite.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tae.a117roomsqlite.App.App;
import com.tae.a117roomsqlite.DI.AppModule;
import com.tae.a117roomsqlite.DI.DaggerAppComponent;
import com.tae.a117roomsqlite.DI.RoomModule;
import com.tae.a117roomsqlite.repository.AppDatabase;
import com.tae.a117roomsqlite.DATA.UserDao;
import com.tae.a117roomsqlite.DATA.User;
import com.tae.a117roomsqlite.R;
import com.tae.a117roomsqlite.repository.UserDataSource;

import javax.inject.Inject;

public class UserActivity extends AppCompatActivity {
 
    private EditText nameBox;
    private EditText yearBox;
    private Button delButton;
    private Button saveButton;
    UserDao userDao;
    SaveUser saveUser;
    @Inject
    UserDataSource userDataSource;

//    private DatabaseAdapter adapter;
    private long userId=0;
    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // достаем объект базы данных.

        AppDatabase adb = App.getInstance().getDatabase();
        userDao= adb.userDao();
        //
        DaggerAppComponent.builder()
                .appModule(new AppModule(getApplication()))
                .roomModule(new RoomModule(getApplication()))
                .build()
                .inject(this);
        System.out.println("///////////////////////////////" + userDataSource);

        saveUser = new SaveUser();

        nameBox = (EditText) findViewById(R.id.name);
        yearBox = (EditText) findViewById(R.id.year);
        delButton = (Button) findViewById(R.id.deleteButton);
        saveButton = (Button) findViewById(R.id.saveButton);
      //  adapter = new DatabaseAdapter(this);
 
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getLong("id");
        }
        // если 0, то добавление
        if (userId > 0) {

          FindById findById = new FindById();
          findById.execute(userId);

        } else {
            // скрываем кнопку удаления
            delButton.setVisibility(View.GONE);
        }
    }
 
    public void save(View view){

        String name = nameBox.getText().toString();
        int year = Integer.parseInt(yearBox.getText().toString());
        User user = new User(userId, name, year);

        if (userId > 0) {
        UpdateUser user1 = new UpdateUser();
        user1.execute(user);
        }

        else {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+user);
        saveUser.execute(user);
        }
        goHome();
        }

    public void delete(View view){

        goHome();
    }
    private void goHome(){
        // переход к главной activity
        Intent intent = new Intent(this, MainActivity.class);
     //   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }


    public class SaveUser extends AsyncTask <User, Void, Void> {
        @Override
        protected Void doInBackground(User... user) {
            userDataSource.save(user[0]);
            return null;
        }
    }

    public class UpdateUser extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... users) {
            userDataSource.update(users[0]);
            return null;
        }
    }


    public class FindById extends AsyncTask <Long, Void, User> {
        @Override
        protected User doInBackground(Long... longs) {
            return userDataSource.findById(longs[0]);
        }
        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            nameBox.setText(user.getName());
            yearBox.setText(String.valueOf(user.getYear()));
        }
    }


}