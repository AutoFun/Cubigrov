package com.example.cubigrov;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.cubigrov.fragment.fragmentHome;
import com.example.cubigrov.fragment.fragmentData;
import com.example.cubigrov.fragment.fragmentGarden;
import com.example.cubigrov.fragment.fragmentArchive;

public class home extends AppCompatActivity implements View.OnClickListener{

    //UI Object
    private TextView txt_topbar;
    private TextView Home ;
    private TextView Garden ;
    private TextView Data ;
    private TextView Archive ;
    private FrameLayout ly_content;

    //Fragment Object
    private FragmentManager fManager;
    private fragmentHome fg1;
    private fragmentGarden fg2;
    private fragmentData fg3;
    private fragmentArchive fg4;

    //private fragmentHome fg1;
    //private fragmentGarden fg2;
    //private fragmentData fg3;
    //private fragmentArchive fg4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home);
        fManager = getFragmentManager();
        bindViews();
        Home.performClick();   //for the first click
    }

    //bingViews
    private void bindViews() {
        txt_topbar = (TextView) findViewById(R.id.txt_topbar);
        Home = (TextView) findViewById(R.id.home);
        Garden = (TextView) findViewById(R.id.garden);
        Data = (TextView) findViewById(R.id.data);
        Archive = (TextView) findViewById(R.id.archive);
        ly_content = (FrameLayout) findViewById(R.id.ly_content);

        Home.setOnClickListener(this);
        Garden.setOnClickListener(this);
        Data.setOnClickListener(this);
        Archive.setOnClickListener(this);
    }

    //reset
    private void setSelected(){
        Home.setSelected(false);
        Garden.setSelected(false);
        Data.setSelected(false);
        Archive.setSelected(false);
    }

    //hide all the Fragments
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
        if(fg4 != null)fragmentTransaction.hide(fg4);
    }


    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()){
            case R.id.home:
                setSelected();
                Home.setSelected(true);
                if(fg1 == null){
                    fg1 = new fragmentHome();
                    fTransaction.add(R.id.ly_content,fg1);
                }else{
                    fTransaction.show(fg1);
                }
                break;
            case R.id.garden:
                setSelected();
                Garden.setSelected(true);
                if(fg2 == null){
                    fg2 = new fragmentGarden();
                    fTransaction.add(R.id.ly_content,fg2);
                }else{
                    fTransaction.show(fg2);
                }
                break;
            case R.id.data:
                setSelected();
                Data.setSelected(true);
                if(fg3 == null){
                    fg3 = new fragmentData();
                    fTransaction.add(R.id.ly_content,fg3);
                }else{
                    fTransaction.show(fg3);
                }
                break;
            case R.id.archive:
                setSelected();
                Archive.setSelected(true);
                if(fg4 == null){
                    fg4 = new fragmentArchive();
                    fTransaction.add(R.id.ly_content,fg4);
                }else{
                    fTransaction.show(fg4);
                }
                break;
        }
        fTransaction.commit();
    }
}
