package com.example.cubigrov.sensor;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cubigrov.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * written by dumi 2020 Autunm sesseion.
 * AI feature?
 * Images detecting feature?
 */

public class sensorData extends AppCompatActivity implements SensorEventListener  {
    private ImageView mBackImageView;
    String currentImagePath = null;
    private static final int IMAGE_REQUEST= 1;
    private TextView ambientValue, lightValue, pressureValue, humidityValue, search;
    private TextView[] valueFields = new TextView[4];

    private final int AMBIENT=0;
    private final int LIGHT=1;
    private final int PRESSURE=2;
    private final int HUMIDITY=3;

    private SensorManager senseManage;
    private Sensor ambTempSense;
    private Sensor luxSense;
    private Sensor pressureSense;
    private Sensor humiditySense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensordata);
        mBackImageView = findViewById(R.id.sensordata_back);
        mBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ambientValue = (TextView)findViewById(R.id.ambient_text);
        valueFields[AMBIENT]=ambientValue;
        lightValue = (TextView)findViewById(R.id.light_text);
        valueFields[LIGHT]=lightValue;
        pressureValue = (TextView)findViewById(R.id.pressure_text);
        valueFields[PRESSURE]=pressureValue;
        humidityValue = (TextView)findViewById(R.id.humidity_text);
        valueFields[HUMIDITY]=humidityValue;

        senseManage = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        search = (TextView)findViewById(R.id.search_bar);
    }

    public void captureImage(View view){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager())!=null){
            File imageFile =null;
            try{
                imageFile = getImageFile();
            }catch(IOException e){
                e.printStackTrace();
            }
            if(imageFile != null){
                Uri imageUri = FileProvider.getUriForFile(this,"com.example.android.fileprovider",imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(cameraIntent,IMAGE_REQUEST);
            }
        }
    }

    public void displayImage(View view){
        Intent intent = new Intent(this, displayImage.class);
        intent.putExtra("image_path",currentImagePath);
        startActivity(intent);
    }


    private File getImageFile()throws IOException{
        String timestamp= new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName = "jpg_"+timestamp+" ";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File imageFile = File.createTempFile(imageName, ".jpg",storageDir);
        currentImagePath=imageFile.getAbsolutePath();
        return imageFile;
    }

    public void showData(View view){
        getAmbientTemp();
        getLumin();
        getPressure();
        getHumidity();

    }
    public void getAmbientTemp(){
        ambTempSense = senseManage.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        senseManage.registerListener(this, ambTempSense, SensorManager.SENSOR_DELAY_NORMAL);
    }
    public void getLumin(){
        luxSense = senseManage.getDefaultSensor(Sensor.TYPE_LIGHT);
        senseManage.registerListener(this, luxSense, SensorManager.SENSOR_DELAY_NORMAL);
    }
    public void getPressure(){
        pressureSense = senseManage.getDefaultSensor(Sensor.TYPE_PRESSURE);;
        senseManage.registerListener(this, pressureSense, SensorManager.SENSOR_DELAY_NORMAL);
    }
    public void getHumidity(){
        humiditySense = senseManage.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        senseManage.registerListener(this, humiditySense, SensorManager.SENSOR_DELAY_NORMAL);
    }
    public void findinDB(View view){
        Toast.makeText(sensorData.this, search.getText(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wikipedia.com/wiki/" + search.getText())));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float sensorValue = event.values[0];
        TextView currValue = ambientValue;
        String envInfo="";
        int currType=event.sensor.getType();
        switch(currType){
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                envInfo=sensorValue+" degrees Celsius";
                currValue=valueFields[AMBIENT];
                ambTempSense=null;
                break;
            case Sensor.TYPE_LIGHT:
                envInfo=sensorValue+" SI lux units";
                currValue=valueFields[LIGHT];
                luxSense=null;
                break;
            case Sensor.TYPE_PRESSURE:
                envInfo=sensorValue+" hPa (millibars)";
                currValue=valueFields[PRESSURE];
                pressureSense=null;
                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                envInfo=sensorValue+" percent humidity";
                currValue=valueFields[HUMIDITY];
                humiditySense=null;
                break;
            default: break;
        }
        currValue.setText(envInfo);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        String accuracyMsg = "";
        switch(accuracy){
            case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
                accuracyMsg="Sensor has high accuracy";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
                accuracyMsg="Sensor has medium accuracy";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
                accuracyMsg="Sensor has low accuracy";
                break;
            case SensorManager.SENSOR_STATUS_UNRELIABLE:
                accuracyMsg="Sensor has unreliable accuracy";
                break;
            default:
                break;
        }
        Toast accuracyToast = Toast.makeText(this.getApplicationContext(), accuracyMsg, Toast.LENGTH_SHORT);
        accuracyToast.show();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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


}

