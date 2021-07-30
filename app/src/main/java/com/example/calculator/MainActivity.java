package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static String result;
    private static boolean counting = false;
    private static Thread thread;
    private static boolean nd2 = true;
    public static String s;
    public static Button bts;
    public static Button btc;
    public static Button btt;
    public static TextView t;
    public static TextView t1;
    private TabLayout tabs;
    public static MainActivity mainActivity;
    public static ArrayList<View> viewsforbackground = new ArrayList<>();
    public static ArrayList<View> viewsforbackgroundforeground = new ArrayList<>();
    public static ArrayList<View> viewsfortext = new ArrayList<>();



    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        mainActivity = this;
        tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        s = "";
        try{
            try{
                FileInputStream fileInputStream = openFileInput("settings.txt");
                byte[] bytes = new byte[fileInputStream.available()];
                fileInputStream.read(bytes);
                fileInputStream.close();
                Activity1Activity.strings = new String(bytes).split("\n");

            }catch (FileNotFoundException e){
                FileOutputStream fileOutputStream = openFileOutput("settings.txt", MODE_PRIVATE);
                fileOutputStream.write("15\n#000000\n#FF5722\n".getBytes());
                fileOutputStream.close();
                Activity1Activity.strings = "15\n#000000\n#FF5722\n".split("\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }


        viewsforbackground.add(findViewById(R.id.tabs));






    }
    private void us (String g){
        final TextView t;
        if (tabs.getSelectedTabPosition() == 0) t = MainActivity.t;
        else t = MainActivity.t1;
        if (counting) return;
        if (g.equals("#")) {
            if (!s.equals("")) {
                if(!(s.endsWith("sin") || s.endsWith("cos") || s.endsWith("tan") || s.endsWith("ln") || s.endsWith("lg"))) s = s.substring(0, s.length() - 1);
                else {
                    if (s.endsWith("ln") || s.endsWith("lg")) s = s.substring(0, s.length() - 2);
                    else if (s.endsWith("arcsin") || s.endsWith("arccos") || s.endsWith("arctan")) s = s.substring(0, s.length() - 6);
                    else s = s.substring(0, s.length() - 3);
                }
            }
        }else if (g.equals("@")){
            s = "";
        }else {
            s += g;
        }
        if (s.equals("")) {
            t.setText("\nEnter text");
            return;
        }
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                counting = true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            if (thread.isAlive()) t.setText("Counting...");
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }

                    }
                }).start();
                if (tabs.getSelectedTabPosition() == 0) {
                    result = s + "\n" + Math2.forcountwhithoutspace(s);
                }else {
                    result = s + "\n" + Math2a.forcountwhithoutspace(s);
                }
                t.setText(result);
                counting = false;
            }
        });
        thread.start();
    }

    public void onclick (View v){
        Button f = (Button)v;
        us(String.valueOf(f.getText()));
    }
    public void onclickcl (View v){
        us("@");
    }
    public void onclickdel (View v){
        us("#");
    }
    public void onclickfact (View v){
        us("!");
    }
    public void onclickxiny (View v){
        us("^");
    }
    public void onclick1nax (View v){
        us("^(-1)");
    }
    public void onclickdegrad(View v){
        Button f = (Button)v;
        if (f.getText().equals("deg")){
            f.setText("rad");
            Math2.radians = true;
        }else {
            f.setText("deg");
            Math2.radians = false;
        }
    }
    public void onclickf(View v){
        Button f = (Button)v;
        us(f.getText() + "(");
    }
    public void onclick2nd(View v){
        if (nd2){
            btc.setText("arccos");
            bts.setText("arcsin");
            btt.setText("arctan");
            nd2 = false;
        }else {
            btc.setText("cos");
            bts.setText("sin");
            btt.setText("tan");
            nd2 = true;
        }
    }
    public void onclicksqrt(View v){
        us("√");
    }

    public void options(View v){
        Intent intent = new Intent("com.example.calculator.Activity1Activity");
        startActivity(intent);
    }
    public void setBackground(int color){
        viewsforbackground.addAll(Activity1Activity.viewsforbackground);
        viewsforbackground.addAll(PlaceholderFragment.viewsforbackground);
        viewsforbackground.addAll(PlaceholderFragment1.viewsforbackground);
        for (View x: viewsforbackground){
            x.setBackground(new ColorDrawable(color));
        }
    }
    public void setForeground(int color){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.settings_icon);
        bitmap = bitmap.copy(Bitmap.Config.ARGB_8888 , true);
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                if (bitmap.getPixel(i, j) != 0) bitmap.setPixel(i, j, color);
            }
        }
        try {
            PlaceholderFragment1.btnsettings.setBackground(new BitmapDrawable(getResources(), bitmap));
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        viewsforbackgroundforeground.addAll(Activity1Activity.viewsforbackgroundforeground);
        tabs.setSelectedTabIndicatorColor(color);
        for (View x: viewsforbackgroundforeground){
            x.setBackground(new ColorDrawable(color));
        }
        tabs.setTabTextColors(color, color);
        viewsfortext.addAll(Activity1Activity.viewsfortext);
        viewsfortext.addAll(PlaceholderFragment.viewsfortext);
        viewsfortext.addAll(PlaceholderFragment1.viewsfortext);
        for (View x: viewsfortext){
            ((TextView)x).setTextColor(color);
        }
    }
}