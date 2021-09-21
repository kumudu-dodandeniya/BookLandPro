package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminCategory extends AppCompatActivity {

    private ImageView translation, novel;
    private ImageView children, education;
    private ImageView biography, magazines;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        translation = (ImageView) findViewById(R.id.translation);
        novel = (ImageView) findViewById(R.id.navel);
        children = (ImageView) findViewById(R.id.children);
        education = (ImageView) findViewById(R.id.education);
        biography = (ImageView) findViewById(R.id.biography);
        magazines = (ImageView) findViewById(R.id.magazine);



        translation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this,Add_Book.class);
                intent.putExtra("category","translation");
                startActivity(intent);


            }
        });

        novel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this,Add_Book.class);
                intent.putExtra("category","Novel");
                startActivity(intent);


            }
        });

        children.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this,Add_Book.class);
                intent.putExtra("category","Children");
                startActivity(intent);


            }
        });


        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this,Add_Book.class);
                intent.putExtra("category","Education");
                startActivity(intent);


            }
        });

        biography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this,Add_Book.class);
                intent.putExtra("category","Biography");
                startActivity(intent);


            }
        });

        magazines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this,Add_Book.class);
                intent.putExtra("category","Magazines");
                startActivity(intent);


            }
        });
    }
}