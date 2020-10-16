package com.example.cubigrov.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cubigrov.R;
import com.example.cubigrov.mongoDBTest;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;

@SuppressLint("ValidFragment")
public class fragmentHome extends Fragment {

    View mView;
    private CardView HomeCV;

    public fragmentHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fg_home, container, false);



        return mView;
    }



}
