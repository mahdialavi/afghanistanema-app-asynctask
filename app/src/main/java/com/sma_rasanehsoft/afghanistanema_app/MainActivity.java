package com.sma_rasanehsoft.afghanistanema_app;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Indicators.PagerIndicator;
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
    private static long back_pressed;
    private static final int TIME_DELAY = 2000;

    public static String data = "";
    ArrayList<String> urlpics;
    ArrayList<String> names;
    ArrayList<String> id;
    ArrayList<String> id2;
    LinearLayoutManager manager;
    public static Handler handler;
    RecyclerView recyclenews;

    TextView txtafgnews;
    TextView txtmohajerin;
    TextView txtvariousnews;
    TextView Email;


    TextView txtsport;
    TextView txtscience;
    LinearLayout linearRegister;

    SliderLayout sliderShow;
    DrawerLayout drawerLayout;
    ImageView hambergurmenu;
    ImageView imgsearch;
    ArrayList<recycleinfo> recylerinfos;
    ArrayList<String> recycleTitle;
    ArrayList<String> recycleimg;
    ArrayList<String> recycleId;
    ArrayList<String> recycledate;
    Long height;


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        txtafgnews = (TextView) findViewById(R.id.txtafgnews);
        txtmohajerin = (TextView) findViewById(R.id.txtmohajerin);
        txtvariousnews = (TextView) findViewById(R.id.txtvariousnews);
        txtsport = (TextView) findViewById(R.id.txtsport);
        txtscience = (TextView) findViewById(R.id.txtscience);
        Email = (TextView) findViewById(R.id.Email);
        linearRegister = (LinearLayout) findViewById(R.id.btnregister);
        linearRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "مهمان گرامی بخش ورود و ثبت نام در حال حاضر غیر فعال میباشد!", Toast.LENGTH_LONG).show();
            }
        });
        txtafgnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_afnew_wait.class);
                startActivity(intent);
            }
        });
        txtmohajerin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_mohajer_Wait.class);
                startActivity(intent);
            }
        });
        txtvariousnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_various_wait.class);
                startActivity(intent);
            }
        });
        txtsport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivitySportWait.class);
                startActivity(intent);
            }
        });
        txtscience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AcitivitySienceWait.class);
                startActivity(intent);
            }
        });
        Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            Intent intent=new Intent(Intent.ACTION_SENDTO);
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"mdp.alavi@gmail.com"});
//                            intent.putExtra(Intent.EXTRA_SUBJECT,);
//                            intent.putExtra(Intent.EXTRA_TEXT,);
                            intent.setData(Uri.parse("mailto:"));
                            startActivity(intent);

                        }catch (Exception e){

                            Toast.makeText(G.context,"برنامه ای برای ارسال ایمیل یافت نشد",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        recycleimg = new ArrayList<>();
        recycleTitle = new ArrayList<>();
        recycleId = new ArrayList<>();
        recycledate = new ArrayList<>();
        recylerinfos = new ArrayList<>();
        hambergurmenu = (ImageView) findViewById(R.id.hambergurmenu);
        imgsearch = (ImageView) findViewById(R.id.imgsearch);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        sliderShow = (SliderLayout) findViewById(R.id.slider);
        sliderShow.setDuration(10000);
        recyclenews = (RecyclerView) findViewById(R.id.recyclerNews);
        recyclenews.setNestedScrollingEnabled(false);
        manager = new LinearLayoutManager(this);
        recyclenews.setHasFixedSize(true);
        recyclenews.setLayoutManager(manager);

        sliderShow.setPresetTransformer(SliderLayout.Transformer.Fade);
        sliderShow.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
        //sliderShow.setPresetIndicator(PagerIndicator.SCROLL_INDICATOR_START);

        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_Search.class);
                startActivity(intent);
            }
        });
        try {
            JSONObject jsonObject = new JSONObject(data);

            JSONArray jsonTitle = jsonObject.getJSONArray("title");
            JSONArray jsonPic = jsonObject.getJSONArray("pics");
            JSONArray jsonId = jsonObject.getJSONArray("id");
            JSONArray jsondate = jsonObject.getJSONArray("date");
            for (int i = 0; i < jsonTitle.length(); i++) {
                recycleimg.add(jsonPic.getString(i));
                recycleTitle.add(jsonTitle.getString(i));
                recycleId.add(jsonId.getString(i));
                recycledate.add(jsondate.getString(i));
            }
            for (int i = 1; i < recycleTitle.size(); i++) {

                recycleinfo recycleinfo = new recycleinfo();
                recycleinfo.title = recycleTitle.get(i);
                recycleinfo.img = recycleimg.get(i);
                recycleinfo.Id = recycleId.get(i);
                recycleinfo.date = recycledate.get(i);

                recylerinfos.add(recycleinfo);
            }
            recyclenews.setAdapter(new recyclenewsAdaptor(recylerinfos));
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        urlpics = new ArrayList();
//        names = new ArrayList();
        id = new ArrayList();
        id2 = new ArrayList();

        hambergurmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
        for (int i = 0; i < 1; i++) {
           // Toast.makeText(MainActivity.this,recycleimg.get(i), Toast.LENGTH_SHORT).show();

            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView.description(recycleTitle.get(i));
            textSliderView.image("http://afghanistanema.com/" + recycleimg.get(i));
            textSliderView.setScaleType(BaseSliderView.ScaleType.Fit).setOnSliderClickListener(this);
            //textSliderView.description("Game of Thrones").image("http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
            sliderShow.addSlider(textSliderView);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("id", recycleId.get(i));
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
     for (int i = 0; i <1; i++) {
         String id = (String) slider.getBundle().get("id");

      //   Toast.makeText(MainActivity.this,id, Toast.LENGTH_SHORT).show();
//        }
         Intent intent2 = new Intent(G.context, ActivityWait_full_Text.class);
         intent2.putExtra("id", id);
         startActivity(intent2);
     }
    }
    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), "برای خروج از برنامه دکمه بازگشت را بزنید!",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }

//    public void onBackpressed(){
//
//        new AlertDialog.Builder(this)
//                .setTitle("Really Exit?")
//                .setMessage("Are you sure you want to exit?")
//                .setNegativeButton(android.R.string.no, null)
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
// public void onClick(DialogInterface arg0, int arg1) {
//                        MainActivity.super.onBackPressed();
//                    }
//                }).create().show();
//
//    }
}
