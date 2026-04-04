package com.example.kissanconnect;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Schemes_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schemes_screen);

        // 1. Setup RecyclerView (Standard setup)
        RecyclerView rvSchemes = findViewById(R.id.rvSchemes);
        rvSchemes.setLayoutManager(new LinearLayoutManager(this));

        // Mock Data for Schemes (Based on your image)
        List<Scheme> schemeList = new ArrayList<>();
        schemeList.add(new Scheme("PM-KISAN Samman Nidhi", "Ministry of Agriculture",
                "Direct income support of ₹6,000 per year...", "₹6,000/year", "31 Mar 2026"));
        schemeList.add(new Scheme("Pradhan Mantri Fasal Bima Yojana", "Ministry of Agriculture",
                "Comprehensive insurance coverage for crops against natural camities", "Crop insurance coverage", "15 Apr 2026"));
        schemeList.add(new Scheme("Kisan Credit Card", "Ministry of Finance",
                "Short term credit needs for crop cultivation, post-harvest expenses", "Credit upto 3 lakh at 4%", "Ongoing"));
        schemeList.add(new Scheme("Soil Health Card Scheme", "Ministry of Agriculture",
                "Issue of Soil Health Cards to farmers with crop-wise recommendations of nutrients", "Free soil analysis", "Ongoing"));


        schemeAdapter adapter = new schemeAdapter(schemeList);
        rvSchemes.setAdapter(adapter);

        // 2. Setup Navigation Bar
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_schemes); // Set current item as active

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            Intent intent = null;

            if (id == R.id.nav_home) {
                intent = new Intent(Schemes_screen.this, Home_Screen.class);
            } else if (id == R.id.nav_orders) {
                intent = new Intent(Schemes_screen.this, orders_screen.class);
            } else if (id == R.id.nav_profile) {
                intent = new Intent(Schemes_screen.this, profile_screen.class);
            } else if (id == R.id.nav_schemes) {
                return true; // Already here
            }else if (id == R.id.nav_profile) {
                intent = new Intent(Schemes_screen.this, profile_screen.class);
            }

            if (intent != null) {
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                return true;
            }
            return false;
        });
    }
}