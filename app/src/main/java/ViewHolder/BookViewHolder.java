package ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.R;

import Interface.ItemClickListner;

public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtBookName, txtBookDescription , txtBookPrice;
    public ImageView imageView;
    public ItemClickListner listner;

    public BookViewHolder(View itemView){
         super(itemView);

         imageView = (ImageView) itemView.findViewById(R.id.book_image);
        txtBookName = (TextView) itemView.findViewById(R.id.book_name);
        txtBookDescription= (TextView) itemView.findViewById(R.id.book_descrip);
        txtBookPrice = (TextView) itemView.findViewById(R.id.book_price);
    }


    public void setItemClickListner(ItemClickListner listner){

        this.listner = listner;
    }

    @Override
    public void onClick(View view) {
        listner.onClick(view, getAdapterPosition(), false);

    }
}
