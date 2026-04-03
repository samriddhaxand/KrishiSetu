package com.example.kissanconnect;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CropAdapter extends RecyclerView.Adapter<CropAdapter.CropViewHolder> {

    private List<crop> cropList;

    public CropAdapter(List<crop> cropList) {
        this.cropList = cropList;
    }

    @NonNull
    @Override
    public CropViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a single grid item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crop_layout, parent, false);
        return new CropViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CropViewHolder holder, int position) {
        crop crop = cropList.get(position);
        holder.name.setText(crop.name);
        holder.quantity.setText(crop.quantity);
        holder.price.setText(crop.price);
        holder.status.setText(crop.status);
        holder.freshness.setText(crop.freshness);
        holder.imageView.setImageResource(crop.imageRes);

        if (crop.status.equalsIgnoreCase("Low Stock")) {
            holder.status.setText("Low Stock");
            holder.status.setTextColor(Color.parseColor("#E64A19")); // Red/Orange
            holder.status.setBackgroundResource(R.drawable.badge_grey); // Create a red version of the badge
        } else {
            holder.status.setText("Available");
            holder.status.setTextColor(Color.parseColor("#2E7D32"));
            holder.status.setBackgroundResource(R.drawable.badge_green);
        }
    }

    @Override
    public int getItemCount() {
        return cropList.size();
    }

    public static class CropViewHolder extends RecyclerView.ViewHolder {
        TextView name, quantity, price, status, freshness;
        ImageView imageView;

        public CropViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvCropName);
            quantity = itemView.findViewById(R.id.tvCropWeight);
            price = itemView.findViewById(R.id.tvCropPrice);
            status = itemView.findViewById(R.id.tvStatus);
            freshness = itemView.findViewById(R.id.tvFreshness);
            imageView = itemView.findViewById(R.id.ivCropImage);
        }
    }
}