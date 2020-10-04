package com.example.cubigrov.showData;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.view.View;


import com.example.cubigrov.R;
import com.example.cubigrov.showData.getGardenData;


public class plant_history extends AppCompatActivity {

    private CardView cyclamenCV,aloeCV,cyclamen2CV,gmCV;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planthistory);
        findViews();
        cyclamenCV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(plant_history.this, getGardenData.class);
                startActivity(intent);
            }
        });
        aloeCV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(plant_history.this, getGardenData.class);
                startActivity(intent);
            }
        });
        cyclamen2CV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(plant_history.this, getGardenData.class);
                startActivity(intent);
            }
        });
        gmCV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(plant_history.this, getGardenData.class);
                startActivity(intent);
            }
        });
    }

        private void findViews (){
            cyclamenCV=findViewById(R.id.Cyclamen);
            aloeCV=findViewById(R.id.Aloe);
            cyclamen2CV=findViewById(R.id.Cyclamen2);
            gmCV=findViewById(R.id.GymnocalyciumMihanovichii);
        }



}


