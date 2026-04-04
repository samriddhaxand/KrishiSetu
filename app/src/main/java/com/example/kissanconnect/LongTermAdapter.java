package com.example.kissanconnect;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LongTermAdapter extends RecyclerView.Adapter<LongTermAdapter.SupplyViewHolder> {

    private List<LongTermProduct> productList;

    public LongTermAdapter(List<LongTermProduct> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public SupplyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Ensure this layout filename is exactly what you have in res/layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_supply_product, parent, false);
        return new SupplyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SupplyViewHolder holder, int position) {
        if (productList == null || position >= productList.size()) return;

        LongTermProduct product = productList.get(position);

        // Data Binding with basic null checks for the product object
        if (product != null) {
            holder.tvName.setText(product.getName());
            holder.tvFarmer.setText("👤 " + product.getFarmer());
            holder.tvPrice.setText(product.getPrice());
            holder.tvDiscount.setText(product.getDiscount());
            holder.imgProduct.setImageResource(product.getImageResource());

            // Handle Add Button Click
            holder.btnAdd.setOnClickListener(v -> {
                Toast.makeText(v.getContext(),
                        product.getName() + " added to subscription",
                        Toast.LENGTH_SHORT).show();
            });
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ProductDetailActivity.class);
            intent.putExtra("product_name", product.getName());
            intent.putExtra("product_image", product.getImageResource());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList != null ? productList.size() : 0;
    }

    public static class SupplyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvFarmer, tvPrice, tvDiscount;
        ImageView imgProduct, btnAdd;

        public SupplyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvProductName);

            // FIX: Change R.id.tvFarmerName to R.id.tvFarmer to match your XML
            tvFarmer = itemView.findViewById(R.id.tvFarmer);

            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvDiscount = itemView.findViewById(R.id.tvDiscount);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            btnAdd = itemView.findViewById(R.id.btnAdd);
        }
    }
}