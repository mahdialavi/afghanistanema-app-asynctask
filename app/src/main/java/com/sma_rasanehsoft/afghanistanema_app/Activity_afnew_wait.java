package com.sma_rasanehsoft.afghanistanema_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Activity_afnew_wait extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afnew_wait);

//        Bundle bundle = getIntent().getExtras();
//        String id = bundle.getString("id");

        new AsyncTaskAfg("http://afghanistanema.com/afgApp/afgnews.php").execute();
        final Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(!Activity_AfNews.afgnews.equals("")){
                            Intent intent=new Intent(G.context,Activity_AfNews.class);
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
