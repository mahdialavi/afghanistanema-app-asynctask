package com.sma_rasanehsoft.afghanistanema_app;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Handler;

public class Activity_AfNews extends AppCompatActivity {
    public static String data = "";
    public static String afgnews = "";

    ImageView slider;
    ArrayList<String> urlpics;
    ArrayList<String> names;
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
        setContentView(R.layout.activity__af_news);


      Toast.makeText(Activity_AfNews.this,data, Toast.LENGTH_SHORT).show();
        slider = (ImageView) findViewById(R.id.slider);

        recycleimg = new ArrayList<>();
        recycleTitle = new ArrayList<>();
        recycleId = new ArrayList<>();
        recylerinfos = new ArrayList<>();
        recyclenews = (RecyclerView) findViewById(R.id.recyclerNews);
        manager = new LinearLayoutManager(this);
        recyclenews.setHasFixedSize(true);
        recyclenews.setLayoutManager(manager);      //  sliderShow.setPresetTransformer(SliderLayout.Transformer.Fade);
//        try {
//            JSONObject jsonObject=new JSONObject(data);
//
//            JSONArray jsonTitle=jsonObject.getJSONArray("title");
//            JSONArray jsonPic=jsonObject.getJSONArray("pics");
//          //  JSONArray jsonId=jsonObject.getJSONArray("id");
//            // Toast.makeText(G.context, jsonTitle+""+jsonPic, Toast.LENGTH_SHORT).show();
//
//            //  Log.i("LOG", jsonPic+"");
//            for (int i=0;i<jsonTitle.length();i++){
//                recycleimg.add(jsonPic.getString(i));
//                recycleTitle.add(jsonTitle.getString(i));
//                //recycleId.add(jsonId.getString(i));
//            }
//
//            for (int i=0;i<recycleTitle.size();i++){
//                recycleinfo recycleinfo = new recycleinfo();
//                recycleinfo.title = recycleTitle.get(i);
//                recycleinfo.img = recycleimg.get(i);
//            //    recycleinfo.Id = recycleId.get(i);
//                recylerinfos.add(recycleinfo);
//            }
//            recyclenews.setAdapter(new recyclenewsAdaptor(recylerinfos) );
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        urlpics = new ArrayList();
//        names = new ArrayList();
//        hambergurmenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(Gravity.RIGHT);
//            }
//        });
        try {
            JSONObject jsonObject=new JSONObject(afgnews);

            JSONArray jsonTitleaf=jsonObject.getJSONArray("title");
            JSONArray jsonPicaf=jsonObject.getJSONArray("pics");
            //  JSONArray jsonId=jsonObject.getJSONArray("id");
           Toast.makeText(G.context, jsonTitleaf+"", Toast.LENGTH_SHORT).show();

            //  Log.i("LOG", jsonPic+"");
            for (int i=0;i<jsonTitleaf.length();i++){
                recycleimg.add(jsonPicaf.getString(i));
                recycleTitle.add(jsonTitleaf.getString(i));
                //recycleId.add(jsonId.getString(i));
            }

            for (int i=0;i<recycleTitle.size();i++){
                recycleinfo recycleinfo = new recycleinfo();
                recycleinfo.title = recycleTitle.get(i);
                recycleinfo.img = recycleimg.get(i);
                //    recycleinfo.Id = recycleId.get(i);
                recylerinfos.add(recycleinfo);
            }
            recyclenews.setAdapter(new recyclenewsAdaptor(recylerinfos) );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
