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

public class ActivityWait_full_Text extends AppCompatActivity {
    private static long back_pressed;
    private static final int TIME_DELAY = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_full__text);
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        Activity_Full_Text.data = "";

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
        if (networkInfo != null && networkInfo.isConnected()) {
            new AsynkTaskfulltext("http://afghanistanema.com/afgApp/getfulltxt.php", id).execute();

            final Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!Activity_Full_Text.data.equals("")) {
                                Intent intent = new Intent(G.context, Activity_Full_Text.class);
                                startActivity(intent);
                                timer.cancel();
                                finish();
                            } else {
                            }
                        }
                    });
                }
            }, 1, 1000);
        }else {
            Intent intent=new Intent(G.context,Connectivity.class);
            startActivity(intent);
        }

    }   @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), "برای خروج از برنامه دکمه بازگشت را بزنید!",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
}
