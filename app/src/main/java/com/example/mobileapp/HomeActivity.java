package com.example.mobileapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mobileapp.Model.Books;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.ObjectInputStream;

import ViewHolder.BookViewHolder;
import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {
    private Button Logoutbtn;

    private DatabaseReference BooksRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        BooksRef = FirebaseDatabase.getInstance().getReference().child("Books");


       // recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);




        Logoutbtn =(Button) findViewById(R.id.logout_btn);

        Logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Paper.book().destroy();

                Intent intent = new Intent(HomeActivity.this,Main_page.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Books> options = new FirebaseRecyclerOptions.Builder<Books>()
                .setQuery(BooksRef, Books.class).build();

        FirebaseRecyclerAdapter<Books, BookViewHolder> adapter = new FirebaseRecyclerAdapter<Books, BookViewHolder>(options) {
           @Override
            protected void onBindViewHolder(@NonNull  BookViewHolder holder, int position, @NonNull Books model) {

                holder.txtBookName.setText(model.getPname());
                holder.txtBookDescription.setText(model.getDescription());
                holder.txtBookPrice.setText("Price =" + model.getPrice() + "Rs.");
                Picasso.get().load(model.getImage()).into(holder.imageView);



            }
          @NonNull
            @Override
            public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_items_layout,parent,false);
                BookViewHolder holder = new BookViewHolder(view);
                return holder;



            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
