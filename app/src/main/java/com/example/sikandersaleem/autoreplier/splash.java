package com.example.sikandersaleem.autoreplier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splash extends Activity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
String message ="";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {


            public void run() {

                Intent i = new Intent(splash.this, MainActivity.class);
                i.putExtra("message", message);
                i.putExtra("nmber",message);
                i.putExtra("msg",message);
                startActivity(i);


                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}