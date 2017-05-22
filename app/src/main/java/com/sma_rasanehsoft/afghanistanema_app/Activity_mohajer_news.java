package com.sma_rasanehsoft.afghanistanema_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.slider.library.SliderLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Handler;

public class Activity_mohajer_news extends AppCompatActivity {
    public static String afgnews = "";

    ImageView slider;
    LinearLayoutManager manager;
    public static Handler handler;
    RecyclerView recyclenews;



    SliderLayout sliderShow;
    DrawerLayout drawerLayout;
    ImageView hambergurmenu;
    ArrayList<recycleinfo> recylerinfos;
    ArrayList<String>recycleTitle ;
    ArrayList<String> recycleimg;
    ArrayList<String> recycleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mohajer_news);

            //  Toast.makeText(Activity_AfNews.this,afgnews, Toast.LENGTH_SHORT).show();
            slider = (ImageView) findViewById(R.id.slider);

            recycleimg = new ArrayList<>();
            recycleTitle = new ArrayList<>();
            recycleId = new ArrayList<>();
            recylerinfos = new ArrayList<>();
            recyclenews = (RecyclerView) findViewById(R.id.recyclerNews);
            recyclenews.setNestedScrollingEnabled(false);
            manager = new LinearLayoutManager(this);
            recyclenews.setHasFixedSize(true);
            recyclenews.setLayoutManager(manager);      //  sliderShow.setPresetTransformer(SliderLayout.Transformer.Fade);

            hambergurmenu = (ImageView) findViewById(R.id.back);
            hambergurmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(G.context, MainActivity.class);
                startActivity(intent);
            }
        });

            try {
                JSONObject jsonObject=new JSONObject(afgnews);

                JSONArray jsonTitleaf=jsonObject.getJSONArray("title");
                JSONArray jsonPicaf=jsonObject.getJSONArray("pics");
                JSONArray jsonId=jsonObject.getJSONArray("id");

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

