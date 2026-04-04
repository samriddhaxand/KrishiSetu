package com.example.kissanconnect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class schemeAdapter extends RecyclerView.Adapter<schemeAdapter.SchemeViewHolder> {
    private List<Scheme> schemeList;

    public schemeAdapter(List<Scheme> schemeList) {
        this.schemeList = schemeList;
    }

    @NonNull
    @Override
    public SchemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scheme_layout, parent, false);
        return new SchemeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SchemeViewHolder holder, int position) {
        Scheme scheme = schemeList.get(position);
        holder.tvName.setText(scheme.getName());
        holder.tvMinistry.setText(scheme.getMinistry());
        holder.tvDesc.setText(scheme.getDescription());
        holder.tvBenefit.setText(scheme.getBenefit());
        holder.tvDeadline.setText(scheme.getDeadline());

        holder.btnView.setOnClickListener(v -> {
            // Action for viewing details
        });
    }

    @Override
    public int getItemCount() { return schemeList.size(); }

    public static class SchemeViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvMinistry, tvDesc, tvBenefit, tvDeadline;
        Button btnView;

        public SchemeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvSchemeName);
            tvMinistry = itemView.findViewById(R.id.tvMinistry);
            tvDesc = itemView.findViewById(R.id.tvDescription);
            tvBenefit = itemView.findViewById(R.id.tvBenefit);
            tvDeadline = itemView.findViewById(R.id.tvDeadline);
            btnView = itemView.findViewById(R.id.btnViewDetails);
        }
    }
}
