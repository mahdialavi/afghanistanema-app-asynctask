package com.sma_rasanehsoft.afghanistanema_app;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by alavi on 4/30/2017.
 */
public class AsynkTaskSience extends AsyncTask{

    public String link="";
//   public String id="";


    public AsynkTaskSience(String link){
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

            ActivityScience.data=builder.toString();

        }catch (Exception e){

        }
        return "";
    }

}
