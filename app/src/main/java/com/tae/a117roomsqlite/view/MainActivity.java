package com.tae.a117roomsqlite.view;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.tae.a117roomsqlite.DATA.User;
import com.tae.a117roomsqlite.DI.AppModule;
import com.tae.a117roomsqlite.DI.DaggerAppComponent;
import com.tae.a117roomsqlite.DI.RoomModule;
import com.tae.a117roomsqlite.R;
import com.tae.a117roomsqlite.repository.UserDataSource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private ListView userList;
    ArrayAdapter<User> arrayAdapter;
   // UserDao userDao;

    @Inject
    UserDataSource userDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
     //   userDao = App.getInstance().getDatabase().userDao();
        //
        DaggerAppComponent.builder()
                .appModule(new AppModule(getApplication()))
               .roomModule(new RoomModule(getApplication()))
                .build()
              .inject(this);
        System.out.println("+++++++++++++++++++++" + userDataSource);
        userList = (ListView)findViewById(R.id.list);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user =arrayAdapter.getItem(position);

                System.out.println(user + "USER !!!!" + user.getId());

                if(user!=null) {
                    Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                    intent.putExtra("id", user.getId());
                    intent.putExtra("click", 25);
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
//        DatabaseAdapter adapter = new DatabaseAdapter(this);
//        adapter.open();

        //List<User> users = adapter.getUsers();
        //List<User> users = userDao.getAll();
       final ArrayList<User> users = new ArrayList<>();
       userDataSource.findAll().observe(this, new Observer<List<User>>() {
           @Override
           public void onChanged(@Nullable List<User> userlist) {
               users.addAll(userlist);

               System.out.println(users+"$$$$$$$$$$$$$$$$$$$$$$$");
           }
       });

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        userList.setAdapter(arrayAdapter);
      //  adapter.close();
    }
    // по нажатию на кнопку запускаем UserActivity для добавления данных
    public void add(View view){
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }
}