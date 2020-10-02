package com.example.cubigrov.showData;

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

public class plant_history extends Fragment {

    View mView;

    private CardView cyclamenCV,aloeCV,cyclamen2CV,gmCV;

    public plant_history() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.planthistory, container, false);

        initViews();
        return mView;
    }



    private void initViews() {
        cyclamenCV = mView.findViewById(R.id.device);
        cyclamenCV.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),getGardenData.class));
            }
        });

        aloeCV = mView.findViewById(R.id.sensors);
        aloeCV.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),getGardenData.class));
            }
        });


        cyclamen2CV = mView.findViewById(R.id.camera);
        cyclamen2CV.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),getGardenData.class));
            }
        });

         gmCV= mView.findViewById(R.id.history);
         gmCV.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),getGardenData.class));
            }
        });





    }

}


