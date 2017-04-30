package com.sma_rasanehsoft.afghanistanema_app;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by alavi on 4/29/2017.
 */
public class AsyncTaskAfg extends AsyncTask {
    public String link="";


    public AsyncTaskAfg(String link){
        this.link=link;

    }




    @Override
    protected Object doInBackground(Object[] params) {

        try{

            URL url=new URL(link);
            URLConnection connection=url.openConnection();

            BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder=new StringBuilder();

            String line=null;

            while((line=reader.readLine())!=null){
                builder.append(line);

            }

            Activity_AfNews.afgnews=builder.toString();

        }catch (Exception e){

        }
        return "";
    }

}
