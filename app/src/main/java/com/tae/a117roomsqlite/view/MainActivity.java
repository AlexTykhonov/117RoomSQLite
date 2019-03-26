package com.tae.a117roomsqlite.view;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener {
    ArrayAdapter<User> arrayAdapter;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Inject
    UserDataSource userDataSource;
    List<User> mainUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerAppComponent.builder()
                .appModule(new AppModule(getApplication()))
               .roomModule(new RoomModule(getApplication()))
                .build()
              .inject(this);

        recyclerViewAdapter = new RecyclerViewAdapter(mainUsers, this);
        recyclerView = findViewById(R.id.recycler_view_list_row);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(recyclerViewAdapter);

        FindAll findAll= new FindAll();
        findAll.execute();
    }


    @Override
    public void onResume() {
        super.onResume();

    }
    // по нажатию на кнопку запускаем UserActivity для добавления данных
    public void add(View view){
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(User user) {

        System.out.println(user + "USER !!!!" + user.getId());

        if(user!=null) {
            Intent intent = new Intent(getApplicationContext(), UserActivity.class);
            intent.putExtra("id", user.getId());
            intent.putExtra("click", 25);
            startActivity(intent);
        }
    }

    public class FindAll extends AsyncTask <Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
       if (userDataSource.findAll().size()>0) {
           mainUsers= userDataSource.findAll();
           recyclerViewAdapter.setData(mainUsers);
           System.out.println(mainUsers + "___________________________MAIN USERS");
       }

            return null;
        }
    }
}