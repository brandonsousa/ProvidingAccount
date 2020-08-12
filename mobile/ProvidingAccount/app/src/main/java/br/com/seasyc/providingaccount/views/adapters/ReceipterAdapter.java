package br.com.seasyc.providingaccount.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.seasyc.providingaccount.R;
import br.com.seasyc.providingaccount.models.Receipt;
import br.com.seasyc.providingaccount.views.viewholders.VHReceipter;

public class ReceipterAdapter extends RecyclerView.Adapter<VHReceipter> {

    private List<Receipt> receiptList;
    private Context context;

    public ReceipterAdapter(List<Receipt> receiptList, Context context) {
        this.receiptList = receiptList;
        this.context = context;
    }

    @NonNull
    @Override
    public VHReceipter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_receipt, parent, false);
        return new VHReceipter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHReceipter holder, int position) {

        if (receiptList.get(position).getCategory() == "Food") {
            holder.cv_receipter.setBackgroundColor(context.getResources().getColor(R.color.category_food));
        } else if (receiptList.get(position).getCategory() == "Accommodation") {
            holder.cv_receipter.setBackgroundColor(context.getResources().getColor(R.color.category_accommodation));
        } else if (receiptList.get(position).getCategory() == "Transport") {
            holder.cv_receipter.setBackgroundColor(context.getResources().getColor(R.color.category_transport));
        } else if (receiptList.get(position).getCategory() == "Others") {
            holder.cv_receipter.setBackgroundColor(context.getResources().getColor(R.color.category_others));
        }

        holder.name.setText(receiptList.get(position).getName());
        holder.date.setText(receiptList.get(position).getDate());
        Glide.with(context).load(receiptList.get(position).getImg_url()).into(holder.iv_receipt);
        holder.description.setText(receiptList.get(position).getDescription());
        holder.category.setText(receiptList.get(position).getDescription());
        holder.price.setText(receiptList.get(position).getDescription());
        holder.key.setText(receiptList.get(position).getKey());

    }

    @Override
    public int getItemCount() {
        return receiptList.size();
    }
}
