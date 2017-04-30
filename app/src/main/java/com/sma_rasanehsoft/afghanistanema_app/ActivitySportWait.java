package com.sma_rasanehsoft.afghanistanema_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class ActivitySportWait extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_wait);
        new AsyncTaskSport("http://192.168.1.201/afgApp/sport.php").execute();
        final Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(!Activity_Sport.data.equals("")){
                            Intent intent=new Intent(G.context,Activity_Sport.class);
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
