package com.sma_rasanehsoft.afghanistanema_app;

import android.app.Application;
import android.content.Context;

/**
 * Created by alavi on 4/20/2017.
 */
public class G extends Application {
    public static Context context;

    @Override
    public void onCreate() {

        super.onCreate();
        context = getApplicationContext();
    }
}
