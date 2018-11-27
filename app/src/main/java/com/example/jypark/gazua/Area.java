package com.example.jypark.gazua;

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
