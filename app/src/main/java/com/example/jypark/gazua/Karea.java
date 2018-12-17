package com.example.jypark.gazua;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class Karea extends AppCompatActivity {

    private RecyclerView recyclerView;
    private  RViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karea);

        init();


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


    private ArrayList<Coche> getListaCoche() {
        ArrayList<Coche> arrayListCoches = new ArrayList<>();

        arrayListCoches.add(new Coche(R.drawable.seoul,"서울","Seoul"));
        arrayListCoches.add(new Coche(R.drawable.kangwon, "강원도","Gangwon-do"));
        arrayListCoches.add(new Coche(R.drawable.kyunggi,"경기도","Gyeonggi-do"));
        arrayListCoches.add(new Coche(R.drawable.chungcheong,"충청도","Chungcheong-do"));
        arrayListCoches.add(new Coche(R.drawable.jeonla,"전라도","Jeonla-do"));
        arrayListCoches.add(new Coche(R.drawable.kyungsang,"경상도","Kyungsang"));
        arrayListCoches.add(new Coche(R.drawable.jeju,"제주도","Jeju Island"));

        return arrayListCoches;
    }


    private void init() {
        this.recyclerView = findViewById(R.id.recycler_view);
        this.layoutManager = new LinearLayoutManager(getBaseContext());
        this.adapter = new RViewAdapter(getBaseContext(), getListaCoche());

    }
}
