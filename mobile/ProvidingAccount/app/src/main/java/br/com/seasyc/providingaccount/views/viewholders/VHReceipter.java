package br.com.seasyc.providingaccount.views.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import br.com.seasyc.providingaccount.R;

public class VHReceipter extends RecyclerView.ViewHolder {

    public CardView cv_receipter;
    public TextView name, price, date, description, category, key;
    public ImageView iv_receipt;

    public VHReceipter(@NonNull View itemView) {
        super(itemView);

        cv_receipter = itemView.findViewById(R.id.cv_receipt);
        name = itemView.findViewById(R.id.cv_receipt_name);
        price = itemView.findViewById(R.id.cv_receipt_price);
        date = itemView.findViewById(R.id.cv_receipt_date);
        description = itemView.findViewById(R.id.cv_receipt_description);
        category = itemView.findViewById(R.id.cv_receipt_category);
        key = itemView.findViewById(R.id.cv_receipt_key);
        iv_receipt = itemView.findViewById(R.id.cv_receipt_iv);

    }
}
