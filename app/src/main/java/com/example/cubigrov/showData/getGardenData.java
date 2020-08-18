package com.example.cubigrov.showData;

import android.os.Build;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.cubigrov.R;

public class getGardenData extends AppCompatActivity  implements SwipeRefreshLayout.OnRefreshListener{

    private ImageView mBackImageView;
    private WebView mJibenleidatuWV, mDingzhileidatuWV;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gardendata);

        setStatusBar();

        initViews();
    }

    private void initViews() {
        mBackImageView = findViewById(R.id.iv_leida_back);
        mBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mJibenleidatuWV = findViewById(R.id.jibenleidatu);
        mJibenleidatuWV.getSettings().setJavaScriptEnabled(true);
        mJibenleidatuWV.getSettings().setDefaultTextEncodingName("UTF-8");
        mJibenleidatuWV.getSettings().setAppCacheEnabled(true);
        mJibenleidatuWV.loadUrl("file:///android_asset/LD_jibenleidatu");

        mJibenleidatuWV = findViewById(R.id.dingzhileidatu);
        mJibenleidatuWV.getSettings().setJavaScriptEnabled(true);
        mJibenleidatuWV.getSettings().setDefaultTextEncodingName("UTF-8");
        mJibenleidatuWV.getSettings().setAppCacheEnabled(true);
        mJibenleidatuWV.loadUrl("file:///android_asset/LD_dingzhileidatu");

        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl_leida);
        mRefreshLayout.setOnRefreshListener(this);
        //set refresh components color (four choices)
        mRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        // set the distance to activate refresh operation
        mRefreshLayout.setDistanceToTriggerSync(300);
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
        initViews();
        mRefreshLayout.setRefreshing(false);
    }
}
