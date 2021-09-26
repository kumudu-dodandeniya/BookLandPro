package com.example.mobileapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CardDetails extends AppCompatActivity {
    EditText cardnumber, nameoncard, expire_date, cvv;
    Button saveandconfirm;

    DatabaseReference card_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        saveandconfirm = findViewById(R.id.saveandconfirm);
        cardnumber = findViewById(R.id.cardnumber);
        nameoncard = findViewById(R.id.nameoncard);
        expire_date = findViewById(R.id.expire_date);
        cvv = findViewById(R.id.cvv);

        card_details = FirebaseDatabase.getInstance().getReference().child("cardnumber");


        saveandconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insetCardData();
            }
        });
}

            private void insetCardData() {

                String cardnum = cardnumber.getText().toString();
                String Cname = nameoncard.getText().toString();
                String ex_date = expire_date.getText().toString();
                String CVV = cvv.getText().toString();

                Cards cards =new Cards(cardnum, Cname, ex_date, CVV);

                card_details.push().setValue(cards);
                Toast.makeText(CardDetails.this, "Data Inserted!", Toast.LENGTH_SHORT).show();
            }
}



















