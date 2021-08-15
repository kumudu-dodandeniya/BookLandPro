package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class View_Books extends AppCompatActivity {
    private Button edit;
    private Button Add_New;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books);
        edit = findViewById(R.id.btn_edit1);
        Add_New = findViewById(R.id.btn_add);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(View_Books.this,Edit_book.class);
                startActivity(intent);
            }
        });
        Add_New.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(View_Books.this,Add_Book.class);
                startActivity(intent);
            }
        });
    }
}
