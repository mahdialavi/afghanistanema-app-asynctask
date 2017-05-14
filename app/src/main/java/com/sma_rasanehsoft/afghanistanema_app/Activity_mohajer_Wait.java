package com.sma_rasanehsoft.afghanistanema_app;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Activity_mohajer_Wait extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mohajer__wait);
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
                new AsyncTaskmohajer("http://afghanistanema.com/afgApp/getrefugee.php").execute();
                final Timer timer=new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if(!Activity_mohajer_news.afgnews.equals("")){
                                    Intent intent=new Intent(G.context,Activity_mohajer_news.class);
                                    startActivity(intent);
                                    timer.cancel();
                                    finish();
                                }
                            }
                        });
                    }
                },1,1000);
            } else {
            Intent intent=new Intent(G.context,Connectivitymenu.class);
            startActivity(intent);
        }}
        }


