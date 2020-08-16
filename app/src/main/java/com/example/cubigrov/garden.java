package com.example.cubigrov;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.os.Build;
import android.os.Bundle;
import com.example.cubigrov.R;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
public class garden extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }
}
