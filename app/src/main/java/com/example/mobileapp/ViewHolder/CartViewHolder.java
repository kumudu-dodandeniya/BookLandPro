package com.example.mobileapp.ViewHolder;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.Interface.ItemClickListner;
import com.example.mobileapp.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtProductName,textProductPrice,txtProductQuantity;
    private ItemClickListner itemClickListner;

    public CartViewHolder(View itemView) {
        super(itemView);

        txtProductName =itemView.findViewById(R.id.product_name);
        textProductPrice =itemView.findViewById(R.id.product_price);
        txtProductQuantity =itemView.findViewById(R.id.product_quantity);

    }

    @Override
    public void onClick(View v) {
        itemClickListner.onClick(v,getAdapterPosition(),false);
    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }
}
