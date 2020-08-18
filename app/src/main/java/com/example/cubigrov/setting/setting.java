package com.example.cubigrov.setting;


import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.cubigrov.R;

import java.util.ArrayList;
import java.util.List;

public class setting extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mRefreshLayout;
    private ImageView mBackImageView;
    private RecyclerView mRecyclerView;
    private SettingsAdapter settingsAdapter;
    private List<String> data = new ArrayList<>();
    private List<String> intentData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        setStatusBar();
        initData();
        initView();
    }

    private void initData() {

        data.add("setting");
        intentData.add(Settings.ACTION_SETTINGS);
        //data.add("Backup and reset");
        //intentData.add(Settings.ACTION_PRIVACY_SETTINGS);
        data.add("device information");
        intentData.add(Settings.ACTION_DEVICE_INFO_SETTINGS);
        data.add("wireless setting");
        intentData.add(Settings.ACTION_WIFI_SETTINGS);
        data.add("Developer options");
        intentData.add(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
        data.add("Language and input devices");
        intentData.add(Settings.ACTION_INPUT_METHOD_SETTINGS);
        data.add("Internal storage");
        intentData.add(Settings.ACTION_INTERNAL_STORAGE_SETTINGS);
        data.add("External storage");
        intentData.add(Settings.ACTION_MEMORY_CARD_SETTINGS);
        //data.add("Date and time");
        //intentData.add(Settings.ACTION_DATE_SETTINGS);
        data.add("Mobile network settings");
        intentData.add(Settings.ACTION_DATA_ROAMING_SETTINGS);
        data.add("Bluetooth settings");
        intentData.add(Settings.ACTION_BLUETOOTH_SETTINGS);
        data.add("Location service");
        intentData.add(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    }

    private void initView() {
        mBackImageView = findViewById(R.id.setting_back);
        mBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mRecyclerView = findViewById(R.id.recyclerview);
        settingsAdapter = new SettingsAdapter(this, data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // setLayoutManager
        mRecyclerView.setLayoutManager(linearLayoutManager);
        // set adapter
        mRecyclerView.setAdapter(settingsAdapter);
        // set ItemAnimator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // set ItemDecoration
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL));
        settingsAdapter.setItemClickListener(new SettingsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openSystemSetting(intentData.get(position));
            }
        });

        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.setting_refresh);
        mRefreshLayout.setOnRefreshListener(this);
        //set refresh components color (four choices)
        mRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        // set the distance to activate refresh operation
        mRefreshLayout.setDistanceToTriggerSync(300);
    }

    private void openSystemSetting(String intent) {
        Intent mIntent = new Intent(intent);
        startActivity(mIntent);
    }

    //set state-bar
    private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    @Override
    public void onRefresh() {
        initView();
        mRefreshLayout.setRefreshing(false);
    }
}