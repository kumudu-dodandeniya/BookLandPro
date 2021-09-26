package com.example.mobileapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.annotations.NotNull;

import java.util.List;

public class ListAdapter extends ArrayAdapter {


    private Activity mContext;
    List<Cards> cardsList;
    public ListAdapter(List<Cards> cardsList){
        super(null,R.layout.activity_ordersuccess, cardsList);
        this.mContext = mContext;
        this.cardsList = cardsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.activity_ordersuccess, null, true);

        TextView card_nm = listItemView.findViewById(R.id.cardnumber);
        TextView cd_name = listItemView.findViewById(R.id.nameoncard);
        TextView mm_yy = listItemView.findViewById(R.id.expire_date);
        TextView cvv = listItemView.findViewById(R.id.cvv);

        Cards cards = cardsList.get(position);

        card_nm.setText(cards.getCardnum());
        cd_name.setText(cards.getCname());
        mm_yy.setText(cards.getEx_date());
        cvv.setText(cards.getCVV());

        return listItemView;

    }
}

