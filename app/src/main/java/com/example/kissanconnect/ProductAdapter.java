package com.example.kissanconnect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> productList;

    public ProductAdapter(List<Product> productList) { this.productList = productList; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consumer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tvName.setText(product.getName());
        holder.tvFarmer.setText(product.getFarmer());
        holder.tvPrice.setText(product.getPrice());
        holder.img.setImageResource(product.getImageRes());
    }

    @Override
    public int getItemCount() { return productList.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvName, tvFarmer, tvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvProductName);
            tvFarmer = itemView.findViewById(R.id.tvFarmerName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}