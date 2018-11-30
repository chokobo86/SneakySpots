package com.example.jypark.gazua;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    Button start, signUp, test, rank;
    View dialogView, dialogView2, dialogView3;
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

                dialogView = View.inflate(MainActivity.this, R.layout.activity_signup,null);

                final AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("SIGNUP");
                dlg.setIcon(R.drawable.ic_menu_allfriends);
                dlg.setView(dialogView);

                EMAIL = dialogView.findViewById(R.id.EMAIL);
                PASSWORD = dialogView.findViewById(R.id.PASSWORD);

                dlg.setPositiveButton("다음", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sEmail = EMAIL.getText().toString();
                        sPassword = PASSWORD.getText().toString();

                        Log.d("email : ",sEmail);

                        map.put("email",sEmail);
                        map.put("password",sPassword);

//  ================================================================================================ signup2
                        dialogView2 = View.inflate(MainActivity.this, R.layout.activity_signup2,null);

                        AlertDialog.Builder dlg2 = new AlertDialog.Builder(MainActivity.this);
                        dlg2.setTitle("SIGNUP");
                        dlg2.setIcon(R.drawable.ic_menu_allfriends);
                        dlg2.setView(dialogView2);

                        NICKNAME = dialogView2.findViewById(R.id.NICKNAME);
                        NATIONALITY = dialogView2.findViewById(R.id.NATIONALITY);
                        DETAIL = dialogView2.findViewById(R.id.DETAIL);
                        PHOTO = dialogView2.findViewById(R.id.PHOTO);

                        dlg2.setPositiveButton("다음", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sNickname = NICKNAME.getText().toString();
                                sNationality = NATIONALITY.getText().toString();
                                sDetail = DETAIL.getText().toString();
                                sPhoto = PHOTO.getText().toString();

                                Log.d("nickname : ",sNickname);

                                map.put("nickname",sNickname);
                                map.put("nationality",sNationality);
                                map.put("detail",sDetail);
                                map.put("photo",sPhoto);

// ================================================================================================= signup3
                                dialogView3 = View.inflate(MainActivity.this, R.layout.activity_signup3,null);

                                AlertDialog.Builder dlg3 = new AlertDialog.Builder(MainActivity.this);
                                dlg3.setTitle("SIGNUP");
                                dlg3.setIcon(R.drawable.ic_menu_allfriends);
                                dlg3.setView(dialogView3);

                                X = dialogView3.findViewById(R.id.X);
                                Y = dialogView3.findViewById(R.id.Y);

                                dlg3.setPositiveButton("등록", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        sX = X.getText().toString();
                                        sY = Y.getText().toString();

                                        Log.d("X : ",sX);

                                        map.put("x",sX);
                                        map.put("y",sY);

                                        insertUser(map);
                                    }
                                });

                                dlg3.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });

                                dlg3.show();
                            }
                        });

                        dlg2.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        dlg2.show();
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

    public String insertUser(HashMap map){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url;
        String response = "";

        try {
            url = new URL("http://192.168.1.86:8088/Gazua/insertUser.jsp");
            Log.i("url : ","http://192.168.1.86:8088/Gazua/insertUser.jsp");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("email", (String) map.get("email"))
                    .appendQueryParameter("password", (String) map.get("password"))
                    .appendQueryParameter("nickname", (String) map.get("nickname"))
                    .appendQueryParameter("nationality", (String) map.get("nationality"))
                    .appendQueryParameter("detail", (String) map.get("detail"))
                    .appendQueryParameter("photo", (String) map.get("photo"))
                    .appendQueryParameter("x", (String) map.get("x"))
                    .appendQueryParameter("y", (String) map.get("y"));
            String query = builder.build().getEncodedQuery();
            Log.i("query : ", query);
            Log.d("query : ", query);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);

            writer.flush();
            writer.close();
            os.close();

            int responseCode=conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                System.out.println("성공");
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response += line;
                }
            }
            else {
                response = "";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }


}
