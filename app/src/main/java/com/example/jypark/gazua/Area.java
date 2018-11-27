package com.example.jypark.gazua;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Area extends AppCompatActivity {
Button button1,button2,button3,button4,button5,button6,button7;
View toastView;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);
        button7 = (Button)findViewById(R.id.button7);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Hunting_spot.class);
                intent.putExtra("x",37.566481);
                intent.putExtra("y",126.977914);
                startActivityForResult(intent, 0);
            }
        });

       /* button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Area.super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_area);

                 Toast toast=new Toast(Area.this);
                        toastView = View.inflate(Area.this, R.layout.toast1, null);
                        toast.setView(toastView);
                        toast.show();

            }
        });*/


    }
}
