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

public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button register;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name=username.getText().toString().trim();
                String pass=password.getText().toString().trim();
                Log.i("TAG",name+"_"+pass);
                UserService uService=new UserService(RegisterActivity.this);
                User user=new User();
                user.setUsername(name);
                user.setPassword(pass);
                uService.register(user);
                Intent intent = new Intent(RegisterActivity.this, home.class);
                Toast.makeText(RegisterActivity.this, "Log in successfully!", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
    }
    private void findViews() {
        username=(EditText) findViewById(R.id.usernameRegister);
        password=(EditText) findViewById(R.id.passwordRegister);
        register=(Button) findViewById(R.id.Register);
    }

}
