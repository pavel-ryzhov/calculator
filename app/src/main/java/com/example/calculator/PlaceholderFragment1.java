package com.example.calculator;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;

public class PlaceholderFragment1 extends Fragment {

    public static ImageButton btnsettings;
    public static ArrayList<View> viewsforbackground = new ArrayList<>();
    public static ArrayList<View> viewsfortext = new ArrayList<>();

    public static PlaceholderFragment1 newInstance() {
        return new PlaceholderFragment1();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment1, container, false);
        MainActivity.t1 = root.findViewById(R.id.general_textview);
        MainActivity.t1.setMovementMethod(new ScrollingMovementMethod());
        btnsettings = root.findViewById(R.id.btnsettings);
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.settings_icon);
        bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                if (bitmap.getPixel(i, j) != 0) bitmap.setPixel(i, j, Color.parseColor(Activity1Activity.strings[2]));
            }
        }

        btnsettings.setBackground(new BitmapDrawable(MainActivity.mainActivity.getResources(), bitmap));

        viewsforbackground.add(root.findViewById(R.id.constraintlayout));

        viewsfortext.add(root.findViewById(R.id.bdel));
        viewsfortext.add(root.findViewById(R.id.bcl));
        viewsfortext.add(root.findViewById(R.id.general_textview));
        viewsfortext.add(root.findViewById(R.id.bskr));
        viewsfortext.add(root.findViewById(R.id.bp5));
        viewsfortext.add(root.findViewById(R.id.bp4));
        viewsfortext.add(root.findViewById(R.id.bp));
        viewsfortext.add(root.findViewById(R.id.b5));
        viewsfortext.add(root.findViewById(R.id.b4));
        viewsfortext.add(root.findViewById(R.id.b20));
        viewsfortext.add(root.findViewById(R.id.b21));
        viewsfortext.add(root.findViewById(R.id.b25));
        viewsfortext.add(root.findViewById(R.id.b24));
        viewsfortext.add(root.findViewById(R.id.b7));
        viewsfortext.add(root.findViewById(R.id.b8));
        viewsfortext.add(root.findViewById(R.id.b26));
        viewsfortext.add(root.findViewById(R.id.b27));
        viewsfortext.add(root.findViewById(R.id.b10));
        viewsfortext.add(root.findViewById(R.id.b));
        viewsfortext.add(root.findViewById(R.id.b30));
        viewsfortext.add(root.findViewById(R.id.b29));
        viewsfortext.add(root.findViewById(R.id.b13));
        viewsfortext.add(root.findViewById(R.id.b14));

        MainActivity.mainActivity.setBackground(Color.parseColor(Activity1Activity.strings[1]));
        MainActivity.mainActivity.setForeground(Color.parseColor(Activity1Activity.strings[2]));


        return root;
    }
}