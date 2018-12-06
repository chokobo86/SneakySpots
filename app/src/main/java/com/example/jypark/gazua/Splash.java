package com.example.jypark.gazua;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView loading = findViewById(R.id.gif_image);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(loading);
        Glide.with(this).load(R.drawable.loading).into(gifImage);

        thread_sleep sleep = new thread_sleep(this);
        /*Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 3000);*/
        sleep.start();
    }

    class thread_sleep extends Thread {
        Activity thisAct;
        thread_sleep(Activity theAct){
            thisAct = theAct;
        }
        public void run(){
            try{
                sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            Intent intent = new Intent(thisAct,MainActivity.class);
            startActivity(intent);
        }


    }

    @Override
    public void onBackPressed() {

    }

}
/*    private class splashhandler implements Runnable {

        @Override
        public void run() {
            startActivity(new Intent(getApplication(), MainActivity.class));
            Splash.this.finish();
        }
    }*/



