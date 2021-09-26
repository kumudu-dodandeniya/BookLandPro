package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConfirmFinalOrderActivity extends AppCompatActivity {

    private EditText nameEditText,phoneEditText,addressEditText;
    private Button confirmOrderButton;

    private String totalAmount =" ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_order);

        confirmOrderButton =(Button) findViewById(R.id.confirmOrder);
        nameEditText =(EditText) findViewById(R.id.deliveryname);
        phoneEditText =(EditText) findViewById(R.id.deliveryphone);
        addressEditText =(EditText) findViewById(R.id.deliveryaddress);

        totalAmount =getIntent().getStringExtra("Total Price");
        Toast.makeText(this, "Total price =" + totalAmount, Toast.LENGTH_SHORT).show();
    }
}