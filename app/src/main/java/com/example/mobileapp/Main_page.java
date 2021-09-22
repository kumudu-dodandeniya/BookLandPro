package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main_page extends AppCompatActivity {

    private Button joinNowBtn,loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        joinNowBtn = (Button) findViewById(R.id.select_join_now);
        loginBtn = (Button) findViewById(R.id.select_login);

       loginBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = new Intent(Main_page.this,UserLogin.class);
               startActivity(intent);
           }
       });

        joinNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Main_page.this,RegisterActivity.class);
                startActivity(intent);
            }
        });



    }
}