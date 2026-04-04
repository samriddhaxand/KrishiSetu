package com.example.kissanconnect;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<order> orderList;
    private Context context;

    public OrderAdapter(Context context, List<order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_layout, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        order order = orderList.get(position);

        holder.tvName.setText(order.customerName);
        holder.tvLocation.setText(order.location);
        holder.tvStatus.setText(order.status);
        holder.tvOrderId.setText(order.orderId);
        holder.tvTotal.setText("Total: " + order.totalAmount);

        holder.btnDecline.setOnClickListener(v -> {
            int actualPosition = holder.getAdapterPosition();
            orderList.remove(actualPosition);
            notifyItemRemoved(actualPosition);
            notifyItemRangeChanged(actualPosition, orderList.size());
        });

        // Handle Accept Button with Timing Dropdown
        holder.btnAccept.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(context, holder.btnAccept);
            String[] timings = {"3 days", "10 days", "1 month", "3 months", "1 yr", "2 yr"};

            for (String time : timings) {
                popup.getMenu().add(time);
            }

            popup.setOnMenuItemClickListener(item -> {
                // A. Hide the Decline button
                holder.btnDecline.setVisibility(View.GONE);

                // B. Update Accept button text and style
                holder.btnAccept.setText("Ready in: " + item.getTitle());
                holder.btnAccept.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFA000"))); // Orange status

                // C. Expand Accept button to take full width
                // We use LinearLayout.LayoutParams because the buttons are inside a horizontal LinearLayout
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        0, // Width
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        2.0f // New weight (takes the space previously held by both buttons)
                );
                params.setMargins(0, 0, 0, 0); // Remove any margins/gaps
                holder.btnAccept.setLayoutParams(params);


                return true;
            });

            popup.show();
        });
    }

    private void showTimingPopup(View anchor, OrderViewHolder holder) {
        PopupMenu popup = new PopupMenu(context, anchor);
        String[] times = {"3 Days", "10 Days", "1 Month", "3 Months", "1 Year", "2 Years"};

        for (String time : times) {
            popup.getMenu().add(time);
        }

        popup.setOnMenuItemClickListener(item -> {
            holder.btnAccept.setText("Ready in " + item.getTitle());
            holder.btnAccept.setBackgroundColor(Color.parseColor("#FFA000")); // Change color
            return true;
        });
        popup.show();
    }

    @Override
    public int getItemCount() { return orderList.size(); }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvLocation, tvStatus, tvOrderId, tvTotal;
        Button btnAccept, btnDecline;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvCustomerName);
            tvLocation = itemView.findViewById(R.id.tvCustomerLocation);
            tvStatus = itemView.findViewById(R.id.tvStatusBadge);
            tvOrderId = itemView.findViewById(R.id.tvOrderId); // Ensure IDs match item_order.xml
            tvTotal = itemView.findViewById(R.id.tvTotalAmount);
            btnAccept = itemView.findViewById(R.id.btnAccept);
            btnDecline = itemView.findViewById(R.id.btnDecline);
        }
    }
}
