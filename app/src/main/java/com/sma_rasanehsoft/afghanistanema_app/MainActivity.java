package com.sma_rasanehsoft.afghanistanema_app;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    public static String data = "";
    ArrayList<String> urlpics;
    ArrayList<String> names;
    LinearLayoutManager manager;
    public static Handler handler;
    RecyclerView recyclenews;

    Button btn;
    SliderLayout sliderShow;
    DrawerLayout drawerLayout;
    ImageView hambergurmenu;
    ArrayList<recycleinfo> recylerinfos;
    ArrayList<String>recycleTitle ;
    ArrayList<String> recycleimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

//        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
//        Log.i("images", data);

        recycleimg = new ArrayList<>();
        recycleTitle = new ArrayList<>();
        recylerinfos = new ArrayList<>();
        hambergurmenu = (ImageView) findViewById(R.id.hambergurmenu);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        sliderShow = (SliderLayout) findViewById(R.id.slider);
        sliderShow.setDuration(6000);
        recyclenews = (RecyclerView) findViewById(R.id.recyclerNews);
        manager = new LinearLayoutManager(this);
        recyclenews.setHasFixedSize(true);
        recyclenews.setLayoutManager(manager);      //  sliderShow.setPresetTransformer(SliderLayout.Transformer.Fade);

        try {
            JSONObject jsonObject=new JSONObject(data);

            JSONArray jsonTitle=jsonObject.getJSONArray("title");
            JSONArray jsonimages=jsonObject.getJSONArray("introtext");
           // Toast.makeText(G.context, jsonTitle.toString(), Toast.LENGTH_LONG).show();


            for (int i=0;i<jsonTitle.length();i++){

               // recycleimg.add(jsonimages.getString(i));
                recycleTitle.add(jsonTitle.getString(i));
            }

            for (int j=0;j<recycleTitle.size();j++){

                recycleinfo recycleinfo = new recycleinfo();
                recycleinfo.title = recycleTitle.get(j);
              //  recycleinfo.img = recycleimg.get(j);

                recylerinfos.add(recycleinfo);

            }
            recyclenews.setAdapter(new recyclenewsAdaptor(recylerinfos) );

        } catch (JSONException e) {
            e.printStackTrace();
        }

        urlpics = new ArrayList();
        names = new ArrayList();

        hambergurmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });


        urlpics.add("http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        urlpics.add("http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
        urlpics.add("http://cdn3.nflximg.net/images/3093/2043093.jpg");
        urlpics.add("http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        names.add("Phone");
        names.add("Shirt");
        names.add("Laptop");
        names.add("Coolpad");



        for (int i = 0; i < urlpics.size(); i++) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView.image(urlpics.get(i)).setScaleType(BaseSliderView.ScaleType.Fit).setOnSliderClickListener(this);

            sliderShow.addSlider(textSliderView);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", names.get(i));
        }


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(MainActivity.this, "salam", Toast.LENGTH_SHORT).show();
    }
}
