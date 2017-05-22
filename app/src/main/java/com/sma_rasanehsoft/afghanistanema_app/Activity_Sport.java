package com.sma_rasanehsoft.afghanistanema_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Activity_Sport extends AppCompatActivity {
    public static String data = "";

    ImageView slider;
    LinearLayoutManager manager;
    RecyclerView recyclenews;


    ImageView hambergurmenu;
    ArrayList<recycleinfo> recylerinfos;
    ArrayList<String>recycleTitle ;
    ArrayList<String> recycleimg;
    ArrayList<String> recycleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__sport);



        slider = (ImageView) findViewById(R.id.slider);

        recycleimg = new ArrayList<>();
        recycleTitle = new ArrayList<>();
        recycleId = new ArrayList<>();
        recylerinfos = new ArrayList<>();
        recyclenews = (RecyclerView) findViewById(R.id.recyclerNews);
        manager = new LinearLayoutManager(this);
        recyclenews.setHasFixedSize(true);
        recyclenews.setLayoutManager(manager);
        recyclenews.setNestedScrollingEnabled(false);
        //  sliderShow.setPresetTransformer(SliderLayout.Transformer.Fade);
        hambergurmenu = (ImageView) findViewById(R.id.back);
        hambergurmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(G.context, MainActivity.class);
                startActivity(intent);
            }
        });

            try {
                JSONObject jsonObject=new JSONObject(data);

                JSONArray jsonTitleaf=jsonObject.getJSONArray("title");
                JSONArray jsonPicaf=jsonObject.getJSONArray("pics");
                JSONArray jsonId=jsonObject.getJSONArray("id");
             //   Toast.makeText(G.context, jsonId+"", Toast.LENGTH_SHORT).show();

                //  Log.i("LOG", jsonPic+"");
                for (int i=0;i<jsonTitleaf.length();i++){
                    recycleimg.add(jsonPicaf.getString(i));
                    recycleTitle.add(jsonTitleaf.getString(i));
                    recycleId.add(jsonId.getString(i));
                }

                for (int i=0;i<recycleTitle.size();i++){
                    recycleinfo recycleinfo = new recycleinfo();
                    recycleinfo.title = recycleTitle.get(i);
                    recycleinfo.img = recycleimg.get(i);
                    recycleinfo.Id = recycleId.get(i);
                    recylerinfos.add(recycleinfo);
                }
                recyclenews.setAdapter(new RecycleNewsAdapter(recylerinfos) );
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

}
