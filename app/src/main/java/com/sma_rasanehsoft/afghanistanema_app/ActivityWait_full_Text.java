package com.sma_rasanehsoft.afghanistanema_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityWait_full_Text extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_full__text);

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");

        new AsynkTaskfulltext("http://192.168.1.201/afgApp/getfulltxt.php",id).execute();
        final Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!Activity_Full_Text.data.equals("")){
                            Intent intent=new Intent(G.context,Activity_Full_Text.class);
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
