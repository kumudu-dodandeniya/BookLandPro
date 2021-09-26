package com.example.mobileapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RetreiveDataActivity extends AppCompatActivity {

    ListView myListview;
    List<Cards> cardsList;

    DatabaseReference RootRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);
        myListview = findViewById(R.id.myListView);
        cardsList = new ArrayList<>();

        RootRef = FirebaseDatabase.getInstance().getReference("CardDetails");
        RootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                cardsList.clear();
                for (DataSnapshot cardDatasnap : datasnapshot.getChildren()) {
                    Cards cards = cardDatasnap.getValue(Cards.class);
                    cardsList.add(cards);
                }
                ListAdapter adapter = new ListAdapter(RetreiveDataActivity.this.cardsList);
                myListview.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        myListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Cards cards = cardsList.get(position);
                showUpdateDialog(cards.getCardnum(), cards.getCname(), cards.getEx_date(), cards.getCVV());
                return false;
            }
        });
    }
    private void showUpdateDialog(String cardnumber, String cardname, String mm_yy, String cvv){

        AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View mDialogView = inflater.inflate(R.layout.update_dialog, null);
        mDialog.setView(mDialogView);
        EditText update_cardnumber = mDialogView.findViewById(R.id.update_cardnumber);
        EditText update_nameoncard = mDialogView.findViewById(R.id.update_nameoncard);
        EditText update_expire_date = mDialogView.findViewById(R.id.update_expire_date);
        EditText update_cvv = mDialogView.findViewById(R.id.update_cvv);
        Button btnUpdate = mDialogView.findViewById(R.id.btnUpdate);
        Button btnDelete = mDialogView.findViewById(R.id.btnDelete);

        mDialog.setTitle("Updating" +cardnumber+ "record");
        mDialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cardnumber= update_cardnumber.getText().toString();
                String mm_yy= update_expire_date.getText().toString();
                String cvv= update_cvv.getText().toString();
                String cardname= update_nameoncard.getText().toString();
                updateDate(cardnumber, cardname, mm_yy, cvv);

                Toast.makeText(RetreiveDataActivity.this, "Record Update", Toast.LENGTH_SHORT).show();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRecord(cardnumber);
            }
        });
    }
    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void deleteRecord(String cardnumber){
        DatabaseReference DbRef =FirebaseDatabase.getInstance().getReference("CardDetails").child(cardnumber);
        Task<Void> mTask = DbRef.removeValue();
        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                showToast("Deleted");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showToast("error deleting recode");
            }
        });
    }
    private void updateDate(String cardnumber, String cardname, String mm_yy, String cvv){
        DatabaseReference DbRef =FirebaseDatabase.getInstance().getReference("CardDetails").child(cardnumber);
        Cards cards = new Cards( cardnumber, cardname, mm_yy, cvv);
    }
}
