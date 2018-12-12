package com.example.jypark.gazua;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class CarouselActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    View dialogView;
    NavigationView navigationView2;
    CarouselView carouselView;


    int [] sampleImages = {R.drawable.bloom,R.drawable.forest,R.drawable.man,R.drawable.modiv,R.drawable.sea,
            R.drawable.travel};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel);

        //메뉴 탭------------------------------------------------------------------------------------
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //------------------------------------------------------------------------------------------

        final BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.action_crown:
                        Intent myIntent2=new Intent(getApplicationContext(), RanksActivity.class);     // AndroidManifast.xml 등록
                        startActivity(myIntent2);
                        break;
                    case R.id.action_pig:
                        Intent myIntent1=new Intent(getApplicationContext(), Area.class);     // AndroidManifast.xml 등록
                        startActivity(myIntent1);
                        break;
                    case R.id.action_camera:
                        Toast.makeText(CarouselActivity.this,"Action camera Clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });



    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };


    //메뉴 탭------------------------------------------------------------------------------------
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {


        } else if (id == R.id.nav_gallery) {


        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.logout) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//------------------------------------------------------------------------------------------
}
