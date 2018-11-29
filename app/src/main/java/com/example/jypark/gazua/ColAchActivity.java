package com.example.jypark.gazua;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TabHost;

public class ColAchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colach);

        final GridView gv=(GridView)findViewById(R.id.gridView1);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);

        TabHost tabHost=(TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();        // tab을 그룹으로 묶어줌

        TabHost.TabSpec ts1 = tabHost.newTabSpec("Tab1");
        ts1.setContent(R.id.content1);
        ts1.setIndicator("Collection");
        tabHost.addTab(ts1);

        TabHost.TabSpec ts2 = tabHost.newTabSpec("Tab2");
        ts2.setContent(R.id.content2);
        ts2.setIndicator("Achievment");
        tabHost.addTab(ts2);

    }

    public class MyGridAdapter extends BaseAdapter {
        Context context;

        Integer[] posterID = { R.drawable.collection, R.drawable.collection,
                R.drawable.collection, R.drawable.collection, R.drawable.collection,
                R.drawable.collection, R.drawable.collection, R.drawable.collection,
                R.drawable.collection, R.drawable.collection, R.drawable.collection,
                R.drawable.collection, R.drawable.collection, R.drawable.collection,
                R.drawable.collection, R.drawable.collection, R.drawable.collection,
                R.drawable.collection, R.drawable.collection, R.drawable.collection,
                R.drawable.collection, R.drawable.collection, R.drawable.collection,
                R.drawable.collection, R.drawable.collection, R.drawable.collection,
                R.drawable.collection, R.drawable.collection, R.drawable.collection,
                R.drawable.collection };

        public MyGridAdapter(Context context){
            this.context=context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView=new ImageView(context);
            int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
            int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());

            imageView.setLayoutParams(new GridView.LayoutParams(width  ,height));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5,5,5,5);
            imageView.setImageResource(posterID[position]);

            final int pos=position;

            return imageView;
        }

        @Override
        public int getCount() {
            return posterID.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }
}
