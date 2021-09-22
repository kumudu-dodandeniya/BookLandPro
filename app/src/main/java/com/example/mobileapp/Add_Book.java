package com.example.mobileapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Add_Book extends AppCompatActivity {

    private String CategoryName , Description, Price , Pname , saveCurrentDate, saveCurrentTime;
    private Button AddNewBookButton;
    private ImageView InputBookImage;
    private EditText InputBookName, InputBookDescription, InputBookPrice;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String bookRandomKey , downloadImageUrl  ;
    private StorageReference bookImagesRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        CategoryName = getIntent().getExtras().get("category").toString();
       bookImagesRef = FirebaseStorage.getInstance().getReference().child("Book Image");

        AddNewBookButton = (Button) findViewById(R.id.btn_add_book);
        InputBookImage = (ImageView) findViewById(R.id.book_image);
        InputBookName = (EditText) findViewById(R.id.Book_Name);
        InputBookDescription = (EditText) findViewById(R.id.Book_Description);
        InputBookPrice = (EditText) findViewById(R.id.Book_Price);


        InputBookImage .setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                openGallery();
            }
        });

        AddNewBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ValidateBookData();
            }
        });

    }

    private void openGallery(){

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GalleryPick && resultCode == RESULT_OK && data !=null)
        {
            ImageUri = data.getData();
            InputBookImage.setImageURI(ImageUri);
        }


    }

    private void ValidateBookData()
    {
        Description = InputBookDescription.getText().toString();
        Price= InputBookDescription.getText().toString();
        Pname = InputBookDescription.getText().toString();

        if(ImageUri == null)
        {
            Toast.makeText(this, "Book image is mandatory",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Description))
        {
            Toast.makeText(this, "Book description is mandatory",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Price))
        {
            Toast.makeText(this, "Book price is mandatory",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Pname))
        {
            Toast.makeText(this, "Book name is mandatory",Toast.LENGTH_SHORT).show();
        }
        else
        {
            StoreBookInformation();
        }


    }

    private void StoreBookInformation() {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        bookRandomKey = saveCurrentDate + saveCurrentTime;

        StorageReference  filePath = bookImagesRef.child(ImageUri.getLastPathSegment() + bookRandomKey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                String message = e.toString();
                Toast.makeText(Add_Book.this, "Error:" + message, Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(Add_Book.this, "Book Image upload successfully..", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                        if(task.isSuccessful()){
                            throw task.getException();
                        }
                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(Add_Book.this, "Got The Book Image Successfully..", Toast.LENGTH_SHORT).show();

                            SaveBookInfoToDatabase();
                        }

                    }
                });


            }
        });

    }

    private void SaveBookInfoToDatabase() {
        HashMap<String,Object> bookMap = new HashMap<>();
        bookMap.put("pid",bookRandomKey);
        bookMap.put("date",saveCurrentDate);
        bookMap.put("time",saveCurrentTime);
        bookMap.put("description",Description);
        bookMap.put("image",downloadImageUrl);
        bookMap.put("category",CategoryName);
        bookMap.put("price",Price);
        bookMap.put("pname",Pname);

    }


}