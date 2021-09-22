package com.example.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class orders extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        getSupportActionBar().setTitle("MainActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button butt1 = (Button) findViewById(R.id.button5);
        Button butt2 = (Button) findViewById(R.id.button4);
        butt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CashOnDelivery_Details = new Intent(orders.this, CashOnDelivery_Details.class);
                startActivity(CashOnDelivery_Details);
            }
        });
        butt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartOrder = new Intent(orders.this, cartOrder.class);
                startActivity(cartOrder);
            }
        });
    }
}



