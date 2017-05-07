package com.sma_rasanehsoft.afghanistanema_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Connectivity extends AppCompatActivity {
    TextView connect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connectivity);

        connect= (TextView) findViewById(R.id.connectretry);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final Timer timer = new Timer();
//                timer.scheduleAtFixedRate(new TimerTask() {
//                    @Override
//                    public void run() {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//                NetworkInfo networkInfo = manager.getActiveNetworkInfo();
//                if (networkInfo!=null && networkInfo.isConnected() ){
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
                                        Intent intent = new Intent(G.context, MainActivity.class);
                                        startActivity(intent);

                                        timer.cancel();
                                        finish();
                                    }
                                    else {
                                        Intent intent = new Intent(G.context, Connectivity.class);
                                        startActivity(intent);

                                        Toast.makeText(G.context, "دستگاه شما به اینترنت متصل نیست!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }, 1, 1000);

//                } else {
//                    Intent intent = new Intent(G.context, Connectivity.class);
//                    startActivity(intent);
//
//                    Toast.makeText(G.context, "دستگاه شما به اینترنت متصل نیست!", Toast.LENGTH_LONG).show();
//                }


            }
        });

    }
}
