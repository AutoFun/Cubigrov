package com.example.cubigrov.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cubigrov.R;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class displayImage extends AppCompatActivity {


    ImageView imageView;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayimage);
        imageView = findViewById(R.id.imageView);
        Bitmap bitmap = BitmapFactory.decodeFile(getIntent().getStringExtra("image_path"));
        imageView.setImageBitmap(bitmap);
    }

    public void send(View v){
        Toast.makeText(getApplicationContext(), "Thing 2", Toast.LENGTH_LONG).show();
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(i,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    imageView.setImageBitmap(selectedImage);

                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                    byte[] array = bos.toByteArray();

                    SendImageClient sic = new SendImageClient();
                    sic.execute(array);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Something Wrong", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "No image Selected", Toast.LENGTH_LONG).show();
            }
        }

    }
    public class SendImageClient extends AsyncTask<byte[],Void,Void>
    {

        @Override
        protected Void doInBackground(byte[]                                                                            ... voids) {

            try{
                Socket socket = new Socket("192.168.1.19",5001);

                OutputStream out = socket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(out);
                dos.writeInt(voids[0].length);
                dos.write(voids[0], 0, voids[0].length);

                handler.post(() -> {
                    Toast.makeText(getApplicationContext(),"image sent",Toast.LENGTH_LONG).show();
                });
                dos.close();
                out.close();
                socket.close();

            }catch(IOException e){
                e.printStackTrace();
                handler.post(() -> {
                    Toast.makeText(getApplicationContext(),"i/o exception", Toast.LENGTH_LONG).show();
                });
            }
            return null;
        }
    }
}


