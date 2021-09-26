package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DEO extends AppCompatActivity {

    private static DatabaseReference databaseReference;

    public DEO()
    {
        FirebaseDatabase db =  FirebaseDatabase.getInstance();
        databaseReference = db.getReference(driverDetails.class.getSimpleName());
    }

    public Task<Void> add(addDriverDetails dtl) {

       return databaseReference.push().setValue(dtl);
    }
    public Task<Void> update(String key, HashMap<String, Object> hashMap){

       return databaseReference.child(key).updateChildren(hashMap);
    }
    public static Task<Void> remove(String key){
       return databaseReference.child(key).removeValue();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deo);
    }
}