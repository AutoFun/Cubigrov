package com.example.cubigrov.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cubigrov.R;
import com.example.cubigrov.garden;
import com.example.cubigrov.showData.getGardenData;

public class fragmentGarden extends Fragment {

    View mView;
    private CardView plantsDataCV, DataCV;

    public fragmentGarden() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fg_garden, container, false);

        initViews();

        return mView;
    }

    private void initViews() {
        plantsDataCV = mView.findViewById(R.id.cv_instance_yunying);
        plantsDataCV.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), getGardenData.class));
            }
        });

        DataCV = mView.findViewById(R.id.cv_instance_qiche);
        DataCV.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),getGardenData.class));
            }
        });
    }

}


