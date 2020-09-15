package com.example.cubigrov;


import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.cubigrov.Mongo.MongoDBUtil;

import com.example.cubigrov.R;

import com.example.cubigrov.Mongo.MongoDBDao;

public class TestActivity extends Activity implements OnClickListener{
    /************** Component in Layout. ***************/
    private Button mongoTestBtn1;
    private Button mongoTestBtn2;
    private Button mongoTestBtn3;
    private Button mongoTestBtn4;
    private Button mongoTestBtn5;
    private Button mongoTestBtn6;
    private Button mongoTestBtn7;
    private Button mongoTestBtn8;
    // The Object use to MongoDB Operate.
    private MongoDBDao mongoDbDao;
    // The Collection Name in MongoDB.
    private String collName = "androidDB";

    /************** Operate Code ********************/
    private final int CREATE_COLLECTION_TEST = 100;
    private final int INSERT_TEST = 101;
    private final int INSERT_BATCH_TEST = 102;
    private final int DELETE_BY_ID_TEST = 103;
    private final int DELETE_BY_DBS_TEST = 104;
    private final int UPDATE_TEST = 105;
    private final int FIND_WITH_PAGE_TEST = 106;
    private final int FIND_NOPAGE_TEST = 107;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mongodb_test);

        findViewAndSetListener();

        mongoDbDao = new MongoDBDao();
    }

    private void findViewAndSetListener() {
        mongoTestBtn1 = (Button) findViewById(R.id.mongodb_btn1);
        mongoTestBtn2 = (Button) findViewById(R.id.mongodb_btn2);
        mongoTestBtn3 = (Button) findViewById(R.id.mongodb_btn3);
        mongoTestBtn4 = (Button) findViewById(R.id.mongodb_btn4);
        mongoTestBtn5 = (Button) findViewById(R.id.mongodb_btn5);
        mongoTestBtn6 = (Button) findViewById(R.id.mongodb_btn6);
        mongoTestBtn7 = (Button) findViewById(R.id.mongodb_btn7);
        mongoTestBtn8 = (Button) findViewById(R.id.mongodb_btn8);

        mongoTestBtn1.setOnClickListener(this);
        mongoTestBtn2.setOnClickListener(this);
        mongoTestBtn3.setOnClickListener(this);
        mongoTestBtn4.setOnClickListener(this);
        mongoTestBtn5.setOnClickListener(this);
        mongoTestBtn6.setOnClickListener(this);
        mongoTestBtn7.setOnClickListener(this);
        mongoTestBtn8.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        MyAsyncTast myAsyncTast = new MyAsyncTast();
        switch(v.getId()){
            case R.id.mongodb_btn1:
                myAsyncTast.execute(CREATE_COLLECTION_TEST);
                break;
            case R.id.mongodb_btn2:
                myAsyncTast.execute(INSERT_TEST);
                break;
            case R.id.mongodb_btn3:
                myAsyncTast.execute(INSERT_BATCH_TEST);
                break;
            case R.id.mongodb_btn4:
                myAsyncTast.execute(DELETE_BY_ID_TEST);
                break;
            case R.id.mongodb_btn5:
                myAsyncTast.execute(DELETE_BY_DBS_TEST);
                break;
            case R.id.mongodb_btn6:
                myAsyncTast.execute(UPDATE_TEST);
                break;
            case R.id.mongodb_btn7:
                myAsyncTast.execute(FIND_WITH_PAGE_TEST);
                break;
            case R.id.mongodb_btn8:
                myAsyncTast.execute(FIND_NOPAGE_TEST);
                break;
        }
    }

    class MyAsyncTast extends AsyncTask<Object, Object, Object>{

        @Override
        protected Object doInBackground(Object... params) {
            Object result = null;
            switch(Integer.parseInt(params[0].toString()))
            {
                case CREATE_COLLECTION_TEST:
                    mongoDbDao.createCollectionTest(collName);
                    break;
                case INSERT_TEST:
                    mongoDbDao.insertTest(collName);
                    break;
                case INSERT_BATCH_TEST:
                    mongoDbDao.insertBatchTest(collName);
                    break;
                case DELETE_BY_ID_TEST:
                    result = mongoDbDao.deleteByIdTest(collName);
                    break;
                case DELETE_BY_DBS_TEST:
                    result = mongoDbDao.deleteByDbsTest(collName);
                    break;
                case UPDATE_TEST:
                    result = mongoDbDao.updateTest(collName);
                    break;
                case FIND_WITH_PAGE_TEST:
                    result = mongoDbDao.findWithPageTest(collName);
                    break;
                case FIND_NOPAGE_TEST:
                    result = mongoDbDao.findNoPageTest(collName);
                    break;
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void onPostExecute(Object result) {
            if(result instanceof Integer)
            {
                showDialogWithText("操作结果码："+result.toString());
            }else if(result instanceof List)
            {
                String resText = "";
                for(String res : ((List<String>) result))
                {
                    resText += res + "\n";
                }
                showDialogWithText("操作结果\n："+resText);
            }

            super.onPostExecute(result);
        }
    }

    private void showDialogWithText(String text)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.icon);
        builder.setTitle("MongoDB操作结果");
        builder.setMessage(text);
        builder.setNeutralButton("确定", new DialogInterface.OnClickListener()
        {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }


    @Override
    protected void onDestroy() {
        // Close MongoDB Connection If It is Not Null.
        if(MongoDBUtil.getConnection() != null)
        {
            MongoDBUtil.getConnection().close();
        }
        super.onDestroy();
    }
}