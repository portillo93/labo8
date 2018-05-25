package com.salvador.labo8;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    MoviesAdapter adapter;
    List<Movie> movies;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv=findViewById(R.id.rv);
        adapter=new MoviesAdapter(movies);
        rv.setLayoutManager(new LinearLayoutManager(this));

        AppDatabase db=AppDatabase.getAppDatabase(getApplicationContext());
        movies=db.moviesDAO().getAll();

        new Thread(movies,rv,adapter,db).execute();
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(),CreateMovie.class));
            }
        });
    }
}
