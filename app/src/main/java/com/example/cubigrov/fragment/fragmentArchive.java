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
import com.example.cubigrov.setting.setting;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;

@SuppressLint("ValidFragment")
public class fragmentArchive extends Fragment {

    View mView;
    private CardView settingDataCV;

    public fragmentArchive() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fg_archive, container, false);

        initViews();

        return mView;
    }

    private void initViews() {
        settingDataCV = mView.findViewById(R.id.cv_setting);
        settingDataCV.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), setting.class));
            }
        });
    }

}