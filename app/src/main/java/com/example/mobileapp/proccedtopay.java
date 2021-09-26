package com.example.mobileapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class proccedtopay extends AppCompatActivity {
    private Button proceedtopay;
    private EditText  entername, editTextPhone2, editTextTextPersonName4, editTextTextPersonName5;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proccedtopay);
        proceedtopay =(Button) findViewById(R.id.proceedtopay);
        entername =(EditText) findViewById(R.id.entername);
        editTextPhone2 =(EditText) findViewById(R.id.editTextPhone2);
        editTextTextPersonName4 =(EditText) findViewById(R.id.editTextTextPersonName4);
        editTextTextPersonName5 =(EditText) findViewById(R.id.editTextTextPersonName5);
        loadingBar = new ProgressDialog(this);
        proceedtopay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceedtopay();
            }
        });
    }
    private void proceedtopay()
    {
        String name = entername.getText().toString();
        String phone = editTextPhone2.getText().toString();
        String district= editTextTextPersonName4.getText().toString();
        String address = editTextTextPersonName5.getText().toString();

        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please write your name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(district)){
            Toast.makeText(this, "Please write your district...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(address)){
            Toast.makeText(this, "Please write your address...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatephoneNumber(name, phone, district, address );
        }
    }
    private void ValidatephoneNumber(final String name, final String phone, final String district, final String address) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot)
            {
                if (!(datasnapshot.child("ShippingDetails").child(phone).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone", phone);
                    userdataMap.put("name", name);
                    userdataMap.put("district", district);
                    userdataMap.put("address", address);

                    RootRef.child("ShippingDetails").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(proccedtopay.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                        Intent intent = new Intent(proccedtopay.this, Payment2.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(proccedtopay.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(proccedtopay.this, "This " + phone + " already exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(proccedtopay.this, "Please try again using another phone number.", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(proccedtopay.this, Payment2.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
