package com.example.kissanconnect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class profile_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        // 1. Initialize the TextViews from your Profile Layout
        // Header Details
        TextView tvHeaderName = findViewById(R.id.tvName);

        // Personal Info Card Details
        TextView tvDetailName = findViewById(R.id.tvDetailName); // From layout_profile_personal_info
        TextView tvPhone = findViewById(R.id.tvPhone);
        TextView tvVillage = findViewById(R.id.tvVillage);
        TextView tvExp = findViewById(R.id.tvExperience);

        // Land Details Card
        TextView tvTotalLand = findViewById(R.id.tvTotalLand); // From layout_profile_land_details

        // 2. Access the SharedPreferences (Use the SAME name: "FarmerProfile")
        SharedPreferences pref = getSharedPreferences("FarmerProfile", MODE_PRIVATE);

        // 3. Retrieve the values (The keys must match your saveAllData method exactly)
        String name = pref.getString("full_name", "Farmer Name");
        String phone = pref.getString("phone_number", "No Phone");
        String city = pref.getString("city", "Location not set");
        String exp = pref.getString("experience", "0");
        String area = pref.getString("land_area", "0");

        // 4. Set the data to your UI
        tvHeaderName.setText(name);
        tvDetailName.setText(name);
        tvPhone.setText("+91 " + phone);
        tvVillage.setText(city);
        tvExp.setText(exp + " Years");
        tvTotalLand.setText(area + " Acres");

        // 5. Navigation Bar setup (Home, Orders, Schemes)
        setupBottomNavigation();
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_profile);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, Home_Screen.class));
                return true;
            } else if (id == R.id.nav_orders) {
                startActivity(new Intent(this, orders_screen.class));
                return true;
            } else if (id == R.id.nav_schemes) {
                startActivity(new Intent(this, Schemes_screen.class));
                return true;
            }
            return false;
        });
    }
}