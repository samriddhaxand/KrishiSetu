package com.example.kissanconnect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// Corrected: The class name and the generic type in <...> must match your ViewHolder
public class ConsOrderAdapter extends RecyclerView.Adapter<ConsOrderAdapter.OrderViewHolder> {

    private List<Order_for_consumer> orderList;

    // Corrected: Constructor name must match the class name "ConsOrderAdapter"
    public ConsOrderAdapter(List<Order_for_consumer> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Matches your custom layout for the order card
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.consumer_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order_for_consumer order = orderList.get(position);

        // Basic Data Binding
        holder.tvDate.setText(order.getDate());
        holder.tvStatus.setText(order.getStatus());
        holder.imgProduct.setImageResource(order.getProductImage());
        holder.tvProductName.setText(order.getProductName());
        holder.tvFarmer.setText("By " + order.getFarmerName());
        holder.tvQty.setText("Qty: " + order.getQuantity());
        holder.tvPrice.setText(order.getPrice());

        // 1. Logic for Long Term Badge visibility
        if (order.isLongTerm()) {
            holder.tvLongTerm.setVisibility(View.VISIBLE);
        } else {
            holder.tvLongTerm.setVisibility(View.GONE);
        }

        // 2. Dynamic Status Badge Styling based on the image design
        String status = order.getStatus().toUpperCase();
        switch (status) {
            case "DELIVERED":
                holder.tvStatus.setBackgroundResource(R.drawable.status_badge_delivered); // Green
                break;
            case "CONFIRMED":
                holder.tvStatus.setBackgroundResource(R.drawable.status_badge_confirmed); // Blue
                break;
            case "IN PROGRESS":
                holder.tvStatus.setBackgroundResource(R.drawable.status_badge_progress); // Muted Green
                break;
            default:
                holder.tvStatus.setBackgroundResource(R.drawable.status_badge_default);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return orderList != null ? orderList.size() : 0;
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvStatus, tvProductName, tvFarmer, tvQty, tvPrice, tvLongTerm;
        ImageView imgProduct;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvOrderDate);
            tvStatus = itemView.findViewById(R.id.tvOrderStatus);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvFarmer = itemView.findViewById(R.id.tvFarmerName);
            tvQty = itemView.findViewById(R.id.tvQuantity);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvLongTerm = itemView.findViewById(R.id.tvLongTermBadge);
        }
    }
}