package com.example.cubigrov.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cubigrov.R;
import com.example.cubigrov.garden;
import com.example.cubigrov.mongoDBTest;
import com.example.cubigrov.sensor.sensorData;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import com.example.cubigrov.sensor.sensorData;
@SuppressLint("ValidFragment")
public class fragmentAI extends Fragment {

    View mView;
    private CardView AIDataCV;

    public fragmentAI() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fg_ai, container, false);

        initViews();

        return mView;
    }

    private void initViews() {
        AIDataCV = mView.findViewById(R.id.cv_AI);
        AIDataCV.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), mongoDBTest.class));
            }
        });
    }

}
