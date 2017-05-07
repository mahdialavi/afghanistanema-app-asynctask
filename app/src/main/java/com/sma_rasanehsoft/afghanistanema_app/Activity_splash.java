package com.sma_rasanehsoft.afghanistanema_app;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

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
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnected() ){
          //  Toast.makeText(G.context, "You are connected", Toast.LENGTH_LONG).show();
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
                                Intent intent = new Intent(Activity_splash.this, MainActivity.class);
                                startActivity(intent);

                                timer.cancel();
                                finish();
                            }
                        }
                    });
                }
            }, 1, 1000);

        } else {
            Intent intent = new Intent(Activity_splash.this, Connectivity.class);
            startActivity(intent);

            Toast.makeText(G.context, "دستگاه شما به اینترنت متصل نیست!", Toast.LENGTH_LONG).show();
        }

//        ConnectivityManager managernet = (ConnectivityManager) getSystemService(G.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = managernet.getActiveNetworkInfo();
//
//
//        if (networkInfo != null && networkInfo.isConnected()) {
//
////        Intent intent = new Intent(MainActivity.this, notconnected.class);
////        startActivity(intent);
//
//            // new Conect("http://hendiabootik.com/android/index.php").execute();
//
//        } else {
//
//            Toast.makeText(G.context, "Your not connected", Toast.LENGTH_LONG).show();
//        }




    }
}
