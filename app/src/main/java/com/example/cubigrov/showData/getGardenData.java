package com.example.cubigrov.showData;

import android.os.Build;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cubigrov.R;
import com.example.cubigrov.mongoDBTest;
import com.google.android.gms.tasks.OnSuccessListener;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.core.auth.StitchUser;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteFindIterable;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential;
import com.mongodb.stitch.core.services.mongodb.remote.RemoteInsertOneResult;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

public class getGardenData extends AppCompatActivity  implements SwipeRefreshLayout.OnRefreshListener{

    private ImageView mBackImageView;
    private WebView mJibenleidatuWV, mDingzhileidatuWV;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gardendata);

        //back button
        mBackImageView = findViewById(R.id.iv_leida_back);
        mBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //setStatusBar
        setStatusBar();
        initViews();

        Stitch.initializeDefaultAppClient(
                getResources().getString(R.string.my_app_id)
        );

        final StitchAppClient stitchAppClient = Stitch.getDefaultAppClient();




        stitchAppClient.getAuth().loginWithCredential(new AnonymousCredential()).addOnSuccessListener(new OnSuccessListener<StitchUser>() {
            @Override
            public void onSuccess(final StitchUser stitchUser) {
                final RemoteMongoClient mongoClient = stitchAppClient.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas");
                RemoteMongoCollection<Document> myCollection = mongoClient.getDatabase("Cubigrov").getCollection("hardware");

                final RemoteFindIterable<Document> query = myCollection.find();
                final ArrayList<Document> result = new ArrayList<Document>();
                query.into(result).addOnSuccessListener(new OnSuccessListener<ArrayList>() {
                    @Override
                    public void onSuccess(ArrayList arrayList) {
                        Log.d("Query",result.toString());
                        String output="";
                        for(Document d : result){

                            output=d.toString();
                            output= output + "\n\n";

                        }
                        TextView text = (TextView)findViewById(R.id.reading_textview);

                        //in your OnCreate() method
                        text.setText(output);
                    }
                });

                RemoteMongoCollection<Document> myCollection2 = mongoClient.getDatabase("Cubigrov").getCollection("requests");

                final RemoteFindIterable<Document> query2 = myCollection2.find();
                final ArrayList<Document> result2 = new ArrayList<Document>();
                query2.into(result2).addOnSuccessListener(new OnSuccessListener<ArrayList>() {
                    @Override
                    public void onSuccess(ArrayList arrayList) {
                        Log.d("Query2",result2.toString());
                        String output2="";
                        for(Document d2 : result2){

                            output2=d2.toString();
                            output2= output2 + "\n\n";

                        }
                        TextView text2 = (TextView)findViewById(R.id.detection_textview);

                        //in your OnCreate() method
                        text2.setText(output2);
                    }
                });


            }

        });

    }

    private void initViews() {
        mJibenleidatuWV = findViewById(R.id.jibenleidatu);
        mJibenleidatuWV.getSettings().setJavaScriptEnabled(true);
        mJibenleidatuWV.getSettings().setDefaultTextEncodingName("UTF-8");
        mJibenleidatuWV.getSettings().setAppCacheEnabled(true);
        mJibenleidatuWV.loadUrl("file:///android_asset/LD_jibenleidatu");

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
