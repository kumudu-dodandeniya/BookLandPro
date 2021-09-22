package com.example.mobileapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CashOnDelivery_Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_on_delivery_details);

        getSupportActionBar().setTitle("orders");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}