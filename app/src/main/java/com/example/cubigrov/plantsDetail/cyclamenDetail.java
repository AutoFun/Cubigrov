package com.example.cubigrov.plantsDetail;


import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cubigrov.R;
import com.example.cubigrov.mongoDBTest;
import com.google.android.gms.tasks.OnSuccessListener;
import com.mongodb.client.model.Filters;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.core.auth.StitchUser;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteFindIterable;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential;
import com.mongodb.stitch.core.services.mongodb.remote.RemoteInsertOneResult;

import org.bson.Document;
import java.util.*;

public class cyclamenDetail extends AppCompatActivity {
    //StitchAppClient stitchAppClient = null;

    private ImageView mBackImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cyc_details);


        Stitch.initializeDefaultAppClient(
                getResources().getString(R.string.my_app_id)
        );

        final StitchAppClient stitchAppClient = Stitch.getDefaultAppClient();




        stitchAppClient.getAuth().loginWithCredential(new AnonymousCredential()).addOnSuccessListener(new OnSuccessListener<StitchUser>() {
            @Override
            public void onSuccess(final StitchUser stitchUser) {
                final RemoteMongoClient mongoClient = stitchAppClient.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas");
                RemoteMongoCollection<Document> myCollection = mongoClient.getDatabase("Cubigrov").getCollection("plantsdb");

                final RemoteFindIterable<Document> query = myCollection.find(Filters.eq("plant_name", "Cyclamen"));
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
                        TextView text = (TextView)findViewById(R.id.cyclamen_textview);

                        //in your OnCreate() method
                        text.setText(output);
                    }
                });


            }

        });
        mBackImageView = findViewById(R.id.cyclamen_back);
        mBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}












