package com.example.cubigrov.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cubigrov.R;
import com.example.cubigrov.garden;
import com.example.cubigrov.home;
import com.example.cubigrov.service.UserService;

import androidx.appcompat.app.AppCompatActivity;

/**
 * by hagnji{
 *  login function
 *  Simply realized with SQLite
 *  Need further development
 * }
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    private EditText username;
    private EditText password;
    private Button login;
    private Button register;

    private void findViews() {
        register=(Button) findViewById(R.id.register);
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        login=(Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=username.getText().toString();
                System.out.println(name);
                String pass=password.getText().toString();
                System.out.println(pass);
                System.out.println("****************************************");
                Log.i("TAG",name+"_"+pass);
                UserService uService=new UserService(LoginActivity.this);
                boolean flag=uService.login(name, pass);
                System.out.println("==============================================");
                if(flag){
                    System.out.println("--------------get Data successfully!-----------------------");
                    Log.i("TAG","Sign in successfully!");
                    Toast.makeText(LoginActivity.this, "Sign in successfully!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, home.class);
                    startActivity(intent);
                }else{
                    Log.i("TAG","Fail to sign in!");
                    Toast.makeText(LoginActivity.this, "Failed!Try again", Toast.LENGTH_LONG).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
