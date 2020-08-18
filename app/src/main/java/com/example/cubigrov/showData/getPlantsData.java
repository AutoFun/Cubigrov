package com.example.cubigrov.showData;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.cubigrov.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class getPlantsData extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private ImageView mBackImageView;
    private ArcView mCTRArvView, mBounceRateArvView;
    private WebView mNewUserWV, mPvUvWV, mProvinceWV, mEndUserWV, mDayEndWV;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantsdata);

        setStatusBar();

        initViews();
    }

    private void initViews() {
        mBackImageView = findViewById(R.id.iv_operation_back);
        mBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mCTRArvView = findViewById(R.id.hardware_battery);
        mCTRArvView.setValues(0, 100, 85, "Hardware");

        mBounceRateArvView = findViewById(R.id.mobile_phone_battery);
        mBounceRateArvView.setValues(0, 100, 66, "Mobile phone");

        mNewUserWV = findViewById(R.id.wv_operation_new_user);
        mNewUserWV.getSettings().setJavaScriptEnabled(true);
        mNewUserWV.getSettings().setDefaultTextEncodingName("UTF-8");
        mNewUserWV.getSettings().setAppCacheEnabled(true);
        mNewUserWV.loadUrl("file:///android_asset/YY_new_user");

        mPvUvWV = findViewById(R.id.wv_operation_pv_uv);
        mPvUvWV.getSettings().setJavaScriptEnabled(true);
        mPvUvWV.getSettings().setDefaultTextEncodingName("UTF-8");
        mPvUvWV.getSettings().setAppCacheEnabled(true);
        mPvUvWV.loadUrl("file:///android_asset/YY_pv_uv");

        mProvinceWV = findViewById(R.id.wv_operation_province);
        mProvinceWV.getSettings().setJavaScriptEnabled(true);
        mProvinceWV.getSettings().setDefaultTextEncodingName("UTF-8");
        mProvinceWV.getSettings().setAppCacheEnabled(true);
        mProvinceWV.loadUrl("file:///android_asset/YY_province");

        mEndUserWV = findViewById(R.id.wv_operation_end_user);
        mEndUserWV.getSettings().setJavaScriptEnabled(true);
        mEndUserWV.getSettings().setDefaultTextEncodingName("UTF-8");
        mEndUserWV.getSettings().setAppCacheEnabled(true);
        mEndUserWV.loadUrl("file:///android_asset/YY_end_user");

        mDayEndWV = findViewById(R.id.wv_operation_day_end);
        mDayEndWV.getSettings().setJavaScriptEnabled(true);
        mDayEndWV.getSettings().setDefaultTextEncodingName("UTF-8");
        mDayEndWV.getSettings().setAppCacheEnabled(true);
        mDayEndWV.loadUrl("file:///android_asset/YY_day_end");

        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl_operation_data);
        mRefreshLayout.setOnRefreshListener(this);
        //����ˢ��ɶʱ���СȦȦ����ɫ���������4��
        mRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        // ������ָ����Ļ�������پ���ᴥ������ˢ��
        mRefreshLayout.setDistanceToTriggerSync(300);
    }

    //���ó���ʽ״̬��
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
        initViews();
        mRefreshLayout.setRefreshing(false);
    }
}

