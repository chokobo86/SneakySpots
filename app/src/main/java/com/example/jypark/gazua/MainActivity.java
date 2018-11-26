package com.example.jypark.gazua;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button start, signUp;
    View dialogView;
    EditText logId, logPass, signId, signPass, signName, signAddr, signPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start=findViewById(R.id.start);
        signUp=findViewById(R.id.signUp);

//START-------------------------------------------------------------------------------------------
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView = View.inflate(MainActivity.this, R.layout.login_main,null);

                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("LOGIN");
                dlg.setIcon(R.drawable.ic_menu_allfriends);
                dlg.setView(dialogView);

                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logId = dialogView.findViewById(R.id.logId);
                        logPass = dialogView.findViewById(R.id.logPass);
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

                dialogView = View.inflate(MainActivity.this, R.layout.sign_main,null);

                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("SIGNUP");
                dlg.setIcon(R.drawable.ic_menu_allfriends);
                dlg.setView(dialogView);

                dlg.setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        signId = dialogView.findViewById(R.id.signId);
                        signPass = dialogView.findViewById(R.id.signPass);
                        signName = dialogView.findViewById(R.id.signName);
                        signPhone = dialogView.findViewById(R.id.signPhone);
                        signAddr = dialogView.findViewById(R.id.signAddr);
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
    }
}
