package com.example.kissanconnect;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Header Data
        String name = getIntent().getStringExtra("product_name");
        int img = getIntent().getIntExtra("product_image", R.drawable.tomatoes);

        ((TextView)findViewById(R.id.detailName)).setText(name);
        ((ImageView)findViewById(R.id.detailImage)).setImageResource(img);
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        setupStatCards();
    }

    private void setupStatCards() {
        // 1. Price Card
        View priceCard = findViewById(R.id.cardPrice);
        ((TextView)priceCard.findViewById(R.id.tvStatLabel)).setText("Price");
        ((TextView)priceCard.findViewById(R.id.tvStatValue)).setText("₹35");
        ((TextView)priceCard.findViewById(R.id.tvStatUnit)).setText("per head");

        // 2. Available Stock Card
        View stockCard = findViewById(R.id.cardStock);
        ((TextView)stockCard.findViewById(R.id.tvStatLabel)).setText("Available Stock");
        ((TextView)stockCard.findViewById(R.id.tvStatValue)).setText("350");
        ((TextView)stockCard.findViewById(R.id.tvStatUnit)).setText("kg");

        // 3. Discount Card (Peach background for Long Term)
        View discCard = findViewById(R.id.cardDiscount);
        discCard.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFF3E0")));
        ((TextView)discCard.findViewById(R.id.tvStatLabel)).setText("Long Term");
        ((TextView)discCard.findViewById(R.id.tvStatValue)).setText("8%");
        ((TextView)discCard.findViewById(R.id.tvStatValue)).setTextColor(Color.parseColor("#EF6C00"));
        ((TextView)discCard.findViewById(R.id.tvStatUnit)).setText("Discount");
    }
}