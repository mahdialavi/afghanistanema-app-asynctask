package com.sma_rasanehsoft.afghanistanema_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class AcitivitySienceWait extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_sience_wait);

        new AsynkTaskSience("http://192.168.1.201/afgApp/science.php").execute();
        final Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(!ActivityScience.data.equals("")){
                            Intent intent=new Intent(G.context,ActivityScience.class);
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
