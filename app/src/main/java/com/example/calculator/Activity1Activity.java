package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Activity1Activity extends AppCompatActivity {
    private EditText background_edittext;
    private EditText foreground_edittext;
    private EditText rounding_edittext;
    private int background, foreground;
    public static ArrayList<View> viewsforbackground = new ArrayList<>();
    public static ArrayList<View> viewsforbackgroundforeground = new ArrayList<>();
    public static ArrayList<View> viewsfortext = new ArrayList<>();
    public static String[] strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        background_edittext = findViewById(R.id.background_edittext);
        rounding_edittext = findViewById(R.id.rounding_edittext);
        foreground_edittext = findViewById(R.id.foreground_edittext);

        viewsforbackground.clear();
        viewsforbackgroundforeground.clear();
        viewsfortext.clear();

        viewsforbackground.add(findViewById(R.id.constraintlayout));
        viewsforbackground.add(findViewById(R.id.rounding_edittext));
        viewsforbackground.add(findViewById(R.id.background_edittext));
        viewsforbackground.add(findViewById(R.id.foreground_edittext));
        viewsforbackground.add(findViewById(R.id.cancel_button));
        viewsforbackground.add(findViewById(R.id.apply_button));
        viewsforbackground.add(findViewById(R.id.reset_button));

        viewsforbackgroundforeground.add(findViewById(R.id.rounding_borders));
        viewsforbackgroundforeground.add(findViewById(R.id.background_borders));
        viewsforbackgroundforeground.add(findViewById(R.id.foreground_borders));
        viewsforbackgroundforeground.add(findViewById(R.id.general_textview));
        viewsforbackgroundforeground.add(findViewById(R.id.apply_borders));
        viewsforbackgroundforeground.add(findViewById(R.id.reset_borders));

        viewsfortext.add(findViewById(R.id.rounding_edittext));
        viewsfortext.add(findViewById(R.id.rounding_label));
        viewsfortext.add(findViewById(R.id.background_edittext));
        viewsfortext.add(findViewById(R.id.background_label));
        viewsfortext.add(findViewById(R.id.foreground_edittext));
        viewsfortext.add(findViewById(R.id.foreground_label));
        viewsfortext.add(findViewById(R.id.cancel_button));
        viewsfortext.add(findViewById(R.id.apply_button));
        viewsfortext.add(findViewById(R.id.reset_button));

        try{
            try{
                FileInputStream fileInputStream = openFileInput("settings.txt");
                byte[] bytes = new byte[fileInputStream.available()];
                fileInputStream.read(bytes);
                fileInputStream.close();
                strings = new String(bytes).split("\n");
                rounding_edittext.setText(strings[0]);
                background_edittext.setText(strings[1]);
                foreground_edittext.setText(strings[2]);

            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        for (View x: Activity1Activity.viewsforbackground){
            x.setBackground(new ColorDrawable(Color.parseColor(strings[1])));
        }
        for (View x: Activity1Activity.viewsforbackgroundforeground){
            x.setBackground(new ColorDrawable(Color.parseColor(strings[2])));
        }
        for (View x: Activity1Activity.viewsfortext){
            ((TextView)x).setTextColor(Color.parseColor(strings[2]));
        }



    }


    public void cancel(View v){
        finish();
    }
    public void apply(View v){
        boolean exception = false;
        try{
            Math2a.accuracy = Integer.parseInt(rounding_edittext.getText().toString());
        }catch (NumberFormatException e){
            exception = true;
            Toast.makeText(this, "Rounding is wrong", Toast.LENGTH_SHORT).show();
        }
        try{
            background = Color.parseColor(background_edittext.getText().toString());
        }catch (IllegalArgumentException e){
            exception = true;
            Toast.makeText(this, "Background is wrong", Toast.LENGTH_SHORT).show();
        }
        try{
            foreground = Color.parseColor(foreground_edittext.getText().toString());
        }catch (IllegalArgumentException e){
            exception = true;
            Toast.makeText(this, "Foreground is wrong", Toast.LENGTH_SHORT).show();
        }


        if (!exception) {
            byte[] bytes = (rounding_edittext.getText().toString() + "\n" + background_edittext.getText().toString() + "\n" + foreground_edittext.getText().toString()).getBytes();
            try {
                try {

                    FileOutputStream fileOutputStream = openFileOutput("settings.txt", MODE_PRIVATE);
                    fileOutputStream.write(bytes);
                    fileOutputStream.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            finish();
            MainActivity.mainActivity.setBackground(background);
            MainActivity.mainActivity.setForeground(foreground);

        }
    }
    public void resettodefault(View v){
        rounding_edittext.setText("15");
        background_edittext.setText("#000000");
        foreground_edittext.setText("#FF5722");
    }
}