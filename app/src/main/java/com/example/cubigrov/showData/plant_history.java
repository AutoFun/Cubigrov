package com.example.cubigrov.showData;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.view.View;
import android.widget.ImageView;


import com.example.cubigrov.R;
import com.example.cubigrov.showData.getGardenData;
import com.example.cubigrov.showData.getPlantsData;



public class plant_history extends AppCompatActivity {

    private CardView cyclamenDataCV,aloeDataCV,cyclamen2DataCV,gmDataCV;
    private ImageView mBackImageView;



    private void findViews (){
        mBackImageView = findViewById(R.id.plant_history_back);
        cyclamenDataCV=findViewById(R.id.CyclamenData);
        aloeDataCV=findViewById(R.id.AloeData);
        cyclamen2DataCV=findViewById(R.id.Cyclamen2Data);
        gmDataCV=findViewById(R.id.GymnData);
    }

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.planthistory);
        findViews();

        mBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cyclamenDataCV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent( plant_history.this, getGardenData.class);
                startActivity(intent);
            }
        });
        aloeDataCV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(plant_history.this, getPlantsData.class);
                startActivity(intent);
            }
        });
        cyclamen2DataCV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(plant_history.this, getGardenData.class);
                startActivity(intent);
            }
        });

        gmDataCV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(plant_history.this, getPlantsData.class);
                startActivity(intent);
            }
        });
    }





}


