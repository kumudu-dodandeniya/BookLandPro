package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addDriverDetails extends AppCompatActivity {
    TextView d1;
    TextView dn2;
    TextView dn3;
Button savebt;
DatabaseReference reff;
driverDetails driverDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver_details);
        d1= (TextView) findViewById(R.id.d1);
        dn2= (TextView) findViewById(R.id.dn2);
        dn3= (TextView) findViewById(R.id.dn3);
        savebt=(Button) findViewById(R.id.savebt);
        driverDetails=new driverDetails();
        DEO deo = new DEO();

        savebt.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                int phonee=Integer.parseInt(dn3.getText().toString().trim());

                driverDetails.setName(d1.getText().toString().trim());
                driverDetails.setAddress(dn2.getText().toString().trim());
                driverDetails.setPhone(phonee);

                reff.push().setValue(driverDetails);
                Toast.makeText(addDriverDetails.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
            }
        });
        reff= FirebaseDatabase.getInstance().getReference().child("driverDetails");



        }
    }
