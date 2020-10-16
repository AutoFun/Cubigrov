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
import com.example.cubigrov.sensor.sensorData;
import com.example.cubigrov.showData.getGardenData;
import com.example.cubigrov.showData.plant_history;

public class fragmentData extends Fragment {

    View mView;

    private CardView deviceCV,sensorsCV,cameraCV,historyCV;

    public fragmentData() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fg_data, container, false);

        initViews();
        return mView;
    }



    private void initViews() {
        deviceCV = mView.findViewById(R.id.device);
        deviceCV.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),getGardenData.class));
            }
        });

        sensorsCV = mView.findViewById(R.id.sensors);
        sensorsCV.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),getGardenData.class));
            }
        });


        cameraCV = mView.findViewById(R.id.camera);
        cameraCV.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), sensorData.class));
            }
        });

        historyCV = mView.findViewById(R.id.history);
        historyCV.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),plant_history.class));
            }
        });





    }

}


