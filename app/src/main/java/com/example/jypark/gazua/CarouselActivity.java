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
    EditText logId, logPass, signId, signPass, signName, signAddr, signPhone;

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
                        dialogView = View.inflate(CarouselActivity.this, R.layout.login_main,null);
                        AlertDialog.Builder dlg = new AlertDialog.Builder(CarouselActivity.this);
                        dlg.setTitle("LOGIN");
                        dlg.setIcon(R.drawable.ic_menu_allfriends);
                        dlg.setView(dialogView);

                        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                logId = dialogView.findViewById(R.id.logId);
                                logPass = dialogView.findViewById(R.id.logPass);
                                Intent myIntent=new Intent(getApplicationContext(), CarouselActivity.class);     // AndroidManifast.xml 등록
                                startActivity(myIntent);
                            }
                        });

                        //--------------------------------------------------------------------------
                        dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                        /*Toast toast=new Toast(MainActivity.this);
                        toastView = View.inflate(MainActivity.this, R.layout.toast1, null);
                        toast.setView(toastView);
                        toast.show();*/
                            }
                        });

                        dlg.show();
                        break;
                    case R.id.action_pig:
                        Toast.makeText(CarouselActivity.this,"Action Hunting Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_camera:
                        Toast.makeText(CarouselActivity.this,"Action Collections Clicked", Toast.LENGTH_SHORT).show();
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
                    case R.id.action_hunting:
                        dialogView = View.inflate(CarouselActivity.this, R.layout.login_main,null);
                        AlertDialog.Builder dlg = new AlertDialog.Builder(CarouselActivity.this);
                        dlg.setTitle("LOGIN");
                        dlg.setIcon(R.drawable.ic_menu_allfriends);
                        dlg.setView(dialogView);

                        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                logId = dialogView.findViewById(R.id.logId);
                                logPass = dialogView.findViewById(R.id.logPass);
                                Intent myIntent=new Intent(getApplicationContext(), CarouselActivity.class);     // AndroidManifast.xml 등록
                                startActivity(myIntent);
                            }
                        });

                        //--------------------------------------------------------------------------
                        dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                        /*Toast toast=new Toast(MainActivity.this);
                        toastView = View.inflate(MainActivity.this, R.layout.toast1, null);
                        toast.setView(toastView);
                        toast.show();*/
                            }
                        });

                        dlg.show();
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
