package com.sma_rasanehsoft.afghanistanema_app;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Activity_splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_splash);
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnected() ) {

            new AsyncTaskNews("http://afghanistanema.com/afgApp/").execute();


            final Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            if (!MainActivity.data.equals("")) {
                                //Toast.makeText(Activity_splash.this, "پست الکترونیکی یا نام کاربری اشتباه است!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(G.context, MainActivity.class);
                                startActivity(intent);

                                timer.cancel();
                                finish();
                            }
                        }
                    });
                }
            }, 1, 1000);}
                  else {
            Intent intent = new Intent(G.context, Connectivity.class);
            startActivity(intent);
        }

    }

}
