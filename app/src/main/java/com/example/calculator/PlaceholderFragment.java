package com.example.calculator;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class PlaceholderFragment extends Fragment {
    public static ArrayList<View> viewsforbackground = new ArrayList<>();
    public static ArrayList<View> viewsfortext = new ArrayList<>();

    public static PlaceholderFragment newInstance() {
        PlaceholderFragment fragment = new PlaceholderFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment0, container, false);
        MainActivity.btc = root.findViewById(R.id.btc);
        MainActivity.bts = root.findViewById(R.id.bts);
        MainActivity.btt = root.findViewById(R.id.btt);
        MainActivity.t = root.findViewById(R.id.general_textview);
        MainActivity.t.setMovementMethod(new ScrollingMovementMethod());

        viewsforbackground.add(root.findViewById(R.id.constraintlayout));

        viewsfortext.add(root.findViewById(R.id.b0));
        viewsfortext.add(root.findViewById(R.id.b19));
        viewsfortext.add(root.findViewById(R.id.bts));
        viewsfortext.add(root.findViewById(R.id.btt));
        viewsfortext.add(root.findViewById(R.id.btc));
        viewsfortext.add(root.findViewById(R.id.general_textview));
        viewsfortext.add(root.findViewById(R.id.bcl));
        viewsfortext.add(root.findViewById(R.id.bdel));
        viewsfortext.add(root.findViewById(R.id.bskl));
        viewsfortext.add(root.findViewById(R.id.bp5));
        viewsfortext.add(root.findViewById(R.id.bp4));
        viewsfortext.add(root.findViewById(R.id.bp));
        viewsfortext.add(root.findViewById(R.id.bskr));
        viewsfortext.add(root.findViewById(R.id.b5));
        viewsfortext.add(root.findViewById(R.id.b4));
        viewsfortext.add(root.findViewById(R.id.b20));
        viewsfortext.add(root.findViewById(R.id.b21));
        viewsfortext.add(root.findViewById(R.id.b3));
        viewsfortext.add(root.findViewById(R.id.b6));
        viewsfortext.add(root.findViewById(R.id.b7));
        viewsfortext.add(root.findViewById(R.id.b8));
        viewsfortext.add(root.findViewById(R.id.b24));
        viewsfortext.add(root.findViewById(R.id.b25));
        viewsfortext.add(root.findViewById(R.id.b26));
        viewsfortext.add(root.findViewById(R.id.b27));
        viewsfortext.add(root.findViewById(R.id.b9));
        viewsfortext.add(root.findViewById(R.id.b10));
        viewsfortext.add(root.findViewById(R.id.b11));
        viewsfortext.add(root.findViewById(R.id.b12));
        viewsfortext.add(root.findViewById(R.id.b13));
        viewsfortext.add(root.findViewById(R.id.b14));
        viewsfortext.add(root.findViewById(R.id.b29));
        viewsfortext.add(root.findViewById(R.id.b30));


//        MainActivity.mainActivity.setBackground(Color.parseColor(Activity1Activity.strings[1]));
//        MainActivity.mainActivity.setForeground(Color.parseColor(Activity1Activity.strings[2]));



        return root;
    }

}