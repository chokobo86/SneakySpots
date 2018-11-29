package com.example.jypark.gazua;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class CarouselActivity extends AppCompatActivity {
    View dialogView;
    BottomNavigationView bottomNavigationView;
    CarouselView carouselView;

    int [] sampleImages = {R.drawable.bloom,R.drawable.forest,R.drawable.man,R.drawable.modiv,R.drawable.sea,
    R.drawable.travel};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel);


        BottomNavigationView navigationView =findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                switch (menuItem.getItemId())
                {
                    case R.id.action_crown:
                        Toast.makeText(CarouselActivity.this,"Action crown Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_pig:
                        Toast.makeText(CarouselActivity.this,"Action pig Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_camera:
                        Toast.makeText(CarouselActivity.this,"Action camera Clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                switch (menuItem.getItemId())
                {
                    case R.id.action_ranks:
                        Intent myIntent2=new Intent(getApplicationContext(), RanksActivity.class);     // AndroidManifast.xml 등록
                        startActivity(myIntent2);
                        break;
                    case R.id.action_hunting:
                        Intent myIntent1=new Intent(getApplicationContext(), Area.class);     // AndroidManifast.xml 등록
                        startActivity(myIntent1);
                        break;
                    case R.id.action_collections:
                        Intent myIntent=new Intent(getApplicationContext(), ColAchActivity.class);     // AndroidManifast.xml 등록
                        startActivity(myIntent);
                        break;
                }
                return true;

                }

        });


        TextView textView = findViewById(R.id.mTitle);

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);

    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };


}
