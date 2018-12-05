package com.example.jypark.gazua;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Geocoder;
import android.os.StrictMode;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Button start, signUp, test, rank;
    View dialogView;
    EditText logId, logPass, EMAIL, PASSWORD, NICKNAME, NATIONALITY, DETAIL, PHOTO, X, Y;
    String sEmail, sPassword, sNickname, sNationality, sDetail, sPhoto="basic", sX, sY;
    HashMap<String,String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        start=findViewById(R.id.start);
        signUp=findViewById(R.id.signUp);
        test=findViewById(R.id.test);
        rank=findViewById(R.id.rank);

//START-------------------------------------------------------------------------------------------
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView = View.inflate(MainActivity.this, R.layout.activity_login,null);

                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
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
            }
        });


//SIGNUP-------------------------------------------------------------------------------------------
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog1 = new SignUp();
                dialog1.show(getSupportFragmentManager(),"dialog1");
            }
        });



        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(getApplicationContext(), Area.class);  //AndroidManifast.xml 등록
                startActivity(myIntent);
            }
        });


        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(getApplicationContext(), RankInfoActivity.class);  //AndroidManifast.xml 등록
                startActivity(myIntent);
            }
        });
    }
}
