package com.sma_rasanehsoft.afghanistanema_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Activity_splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_splash);
//        new AsyncTaskNews("http://192.168.1.201/afg-app/index.php").execute();


//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        new AsyncTaskNews("http://192.168.1.201/afgApp/").execute();

        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                  if (!MainActivity.data.equals("")) {
                         //Toast.makeText(Activity_splash.this, "پست الکترونیکی یا نام کاربری اشتباه است!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Activity_splash.this, MainActivity.class);
                startActivity(intent);

                            timer.cancel();
                            finish();
                        }
                    }
                });
            }
        }, 1, 1000);
    }
}
