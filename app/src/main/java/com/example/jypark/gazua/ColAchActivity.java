package com.example.jypark.gazua;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.jypark.gazua.Fragment.PagerAdapter;

public class ColAchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colach);
        PagerAdapter mTestPagerAdapter = new PagerAdapter((getSupportFragmentManager()));

        ViewPager mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(mTestPagerAdapter);

        TabLayout mTab = findViewById(R.id.tabs);
        mTab.setupWithViewPager(mViewPager);

    }
}
