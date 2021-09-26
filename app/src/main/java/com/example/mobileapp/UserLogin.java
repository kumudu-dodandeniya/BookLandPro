package com.example.mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileapp.Model.Users;
import com.example.mobileapp.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class UserLogin extends AppCompatActivity {
    private EditText InputPhoneNumber,InputPassword;
    private Button LoginButton;
    private ProgressDialog loadingBar;
    private String parentDbname ="Users";
    private CheckBox chkBoxRemeberMe;
    private TextView AdminLink, NotAdminLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        LoginButton = (Button) findViewById(R.id.login);
        InputPhoneNumber = (EditText) findViewById(R.id.phonenum);
        InputPassword = (EditText) findViewById(R.id.password);
        AdminLink = (TextView) findViewById(R.id.admin);
        NotAdminLink = (TextView) findViewById(R.id.not_admin);
        loadingBar =new ProgressDialog(this);

        chkBoxRemeberMe =(CheckBox) findViewById(R.id.remember_me) ;
        Paper.init(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginUser();
            }
        });

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginButton.setText("Login Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                parentDbname = "Admins";
            }
        });

        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginButton.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                parentDbname = "Users";
            }
        });

    }

    private void LoginUser() {

        String phonenumber =InputPhoneNumber.getText().toString();
        String password =InputPassword.getText().toString();

        if(TextUtils.isEmpty(phonenumber)){
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }

        else{
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait,while we are checking for credentials...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToTheAccount(phonenumber,password);
        }
    }

    private void AllowAccessToTheAccount(String phonenumber, String password) {

        if(chkBoxRemeberMe.isChecked()){
            Paper.book().write(Prevalent.UserPhoneKey,phonenumber);
            Paper.book().write(Prevalent.UserPasswordKey,password);
        }

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(parentDbname).child(phonenumber).exists()){
                    Users usersData= dataSnapshot.child(parentDbname).child(phonenumber).getValue(Users.class);

                    if(usersData.getPhonenumber().equals(phonenumber)){

                        if(usersData.getPassword().equals(password)){
                            if(parentDbname.equals("Admins")){
                                Toast.makeText(UserLogin.this, "Welcome Admin, you are logged in Successfully", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(UserLogin.this,AdminCategory.class);
                                startActivity(intent);
                            }
                            else if(parentDbname.equals("Users")){
                                Toast.makeText(UserLogin.this, "logged in Successfully", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(UserLogin.this,HomeActivity.class);
                                Prevalent.currentOnlineUser = usersData;
                                startActivity(intent);
                            }
                        }
                        else{
                            loadingBar.dismiss();
                            Toast.makeText(UserLogin.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                else{
                    Toast.makeText(UserLogin.this, "Account with this" +phonenumber +"do not exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}