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
  //  public String id="";


    public AsyncTaskAfg(String link){

        this.link=link;
      //  this.id=id;

    }




    @Override
    protected Object doInBackground(Object[] params) {

        try{
         //   String data= URLEncoder.encode("id","UTF8")+"="+URLEncoder.encode(id,"UTF8");

            URL url=new URL(link);
            URLConnection connection=url.openConnection();

//            connection.setDoOutput(true);
//            OutputStreamWriter writer=new OutputStreamWriter(connection.getOutputStream());
//            writer.write(data);
//            writer.flush();




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
