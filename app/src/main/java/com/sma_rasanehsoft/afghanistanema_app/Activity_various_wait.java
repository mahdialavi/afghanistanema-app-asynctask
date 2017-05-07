package com.sma_rasanehsoft.afghanistanema_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Activity_various_wait extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_various_wait);

        new AsynkTastvarious("http://afghanistanema.com/afgApp/getvarious.php").execute();
        final Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(!Activity_VariousNews.data.equals("")){
                            Intent intent=new Intent(G.context,Activity_VariousNews.class);
                            startActivity(intent);
                            timer.cancel();
                            finish();
                        }
                    }
                });
            }
        },1,1000);

    }
}
