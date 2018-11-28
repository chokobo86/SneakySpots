package com.example.jypark.gazua;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class CarouselActivity extends AppCompatActivity {

    CarouselView carouselView;

    int [] sampleImages = {R.drawable.bloom,R.drawable.forest,R.drawable.man,R.drawable.modiv,R.drawable.sea,
    R.drawable.travel};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carousel_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
               switch (menuItem.getItemId())
               {
                   case R.id.action_hunting:
                       Toast.makeText(CarouselActivity.this,"Action Ranks Clicked", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.action_ranks:
                       Toast.makeText(CarouselActivity.this,"Action Hunting Clicked", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.action_collections:
                       Toast.makeText(CarouselActivity.this,"Action Collections Clicked", Toast.LENGTH_SHORT).show();
                       break;

               }
               return true;
            }
        });

        /*android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = toolbar.findViewById(R.id.toolbarTitle);*/

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
