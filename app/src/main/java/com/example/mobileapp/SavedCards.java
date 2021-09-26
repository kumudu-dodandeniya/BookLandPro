package com.example.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SavedCards extends AppCompatActivity {
    private Button button6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_cards);



    button6 = (Button) findViewById(R.id.cardpayment);
    button6.setOnClickListener(new View.OnClickListener() {
        @Override

        public void onClick(View v) {
            openOrdersuccess();
        }
    });

    }


    public void openOrdersuccess() {
        Intent intent = new Intent(this, Ordersuccess.class);
        startActivity(intent);
    }
}