package com.example.jypark.gazua;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.support.v7.app.AlertDialog;

public class RanksActivity extends AppCompatActivity implements View.OnClickListener {
    View dialogView;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranks);
        text = findViewById(R.id.rank1);
        text.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rank1:
                // TextView가 클릭될 시 할 코드작성
                dialogView = View.inflate(RanksActivity.this, R.layout.activity_rankinfo,null);
                        AlertDialog.Builder dlg = new AlertDialog.Builder(RanksActivity.this);
                        dlg.setView(dialogView);
                        dlg.show();
                break;
        }
    }
}