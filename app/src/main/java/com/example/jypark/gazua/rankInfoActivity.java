package com.example.jypark.gazua;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

public class rankInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);


        setTitle("OTHER USER INFO");
       // Gallery gallery=findViewById(R.id.gallery1);
       HorizontalScrollView gallery=(HorizontalScrollView)findViewById(R.id.gallery1);

      MyGalleryAdapter galAdapter = new MyGalleryAdapter(this);
        //gallery.setAdapter(galAdapter);

    }

    public class MyGalleryAdapter extends BaseAdapter {
        Context context;

        Integer[] posterID = { R.drawable.mov01, R.drawable.mov02,
                R.drawable.mov03, R.drawable.mov04, R.drawable.mov05,
                R.drawable.mov06, R.drawable.mov07, R.drawable.mov08,
                R.drawable.mov09, R.drawable.mov10 };

        public MyGalleryAdapter(Context context){
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView=new ImageView(context);
            imageView.setLayoutParams(new Gallery.LayoutParams(100,150));
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
