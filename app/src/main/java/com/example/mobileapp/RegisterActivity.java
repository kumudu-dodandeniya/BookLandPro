package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private Button CreateAccount;
    private EditText InputUsername ,InputPhoneNumber,InputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        CreateAccount = (Button) findViewById(R.id.register);
        InputUsername = (EditText) findViewById(R.id.reg_username);
        InputPhoneNumber = (EditText) findViewById(R.id.reg_phone);
        InputPassword = (EditText) findViewById(R.id.reg_password);
    }
}