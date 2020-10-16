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
import com.example.cubigrov.sensor.sensorData;
import com.example.cubigrov.showData.getGardenData;
import com.example.cubigrov.showData.plant_history;

public class fragmentGarden extends Fragment {

    View mView;

    private CardView cyclamenCV,aloeCV,cyclamen2CV,gmCV;

    public fragmentGarden() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fg_plants, container, false);

        initViews();
        return mView;
    }



    private void initViews() {
        cyclamenCV = mView.findViewById(R.id.Cyclamen);
        cyclamenCV.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),getGardenData.class));
            }
        });

        aloeCV = mView.findViewById(R.id.Aloe);
        aloeCV.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),getGardenData.class));
            }
        });


        cyclamen2CV = mView.findViewById(R.id.Cyclamen2);
        cyclamen2CV.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), sensorData.class));
            }
        });

        gmCV = mView.findViewById(R.id.GymnocalyciumMihanovichii);
        gmCV.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),sensorData.class));
            }
        });

    }

}


