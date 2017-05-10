package com.sma_rasanehsoft.afghanistanema_app;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Connectivity extends AppCompatActivity {
    TextView connect;
    private static long back_pressed;
    private static final int TIME_DELAY = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connectivity);

        connect= (TextView) findViewById(R.id.connectretry);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AsyncTaskNews("http://afghanistanema.com/afgApp/").execute();

                ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = manager.getActiveNetworkInfo();
                if (networkInfo!=null && networkInfo.isConnected() ) {
                    if (!MainActivity.data.equals("")) {
                        Intent intent = new Intent(G.context, MainActivity.class);
                        startActivity(intent);
                    }

                }else {
                    Toast.makeText(Connectivity.this, "اتصال به اینترنت برقرار نمی باشد!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }  @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
          super.onBackPressed();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        } else {
            Toast.makeText(getBaseContext(), "برای خروج از برنامه دکمه بازگشت را بزنید!",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
}
