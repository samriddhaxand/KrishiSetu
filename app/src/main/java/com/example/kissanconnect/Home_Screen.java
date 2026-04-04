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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Home_Screen extends AppCompatActivity {

    TextView name,location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_screen);

        RecyclerView recyclerView = findViewById(R.id.rvInventory);
// This creates the 2-column grid
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        List<crop> cropList = new ArrayList<>();
        cropList.add(new crop("Wheat", "500 kg", "₹22/kg", "Available", "Fresh", R.drawable.wheat));
        cropList.add(new crop("Spinach", "200 kg", "₹50/kg", "Available", "Fresh", R.drawable.spinach));
        cropList.add(new crop("Potato", "400 kg", "₹18/kg", "Available", "Fresh", R.drawable.potatoes));
        cropList.add(new crop("Oranges", "200 kg", "₹100/kg", "Available", "Fresh", R.drawable.oranges));
        cropList.add(new crop("Grapes", "250 kg", "₹90/kg", "Available", "Fresh", R.drawable.grapes));
        cropList.add(new crop("Tomatoes", "700 kg", "₹35/kg", "Available", "Very Fresh", R.drawable.tomatoes));
// ... add others

        CropAdapter adapter = new CropAdapter(cropList);
        recyclerView.setAdapter(adapter);

        name = findViewById(R.id.tvHomeUserName);
        location = findViewById(R.id.tvHomeUserLocation);

        SharedPreferences pref = getSharedPreferences("FarmerProfile", MODE_PRIVATE);
        String farmerName = pref.getString("full_name", "Farmer");
        String farmerCity = pref.getString("city", "Location not set");
        name.setText(farmerName);
        location.setText(farmerCity);

        // Inside onCreate of Home_Screen.java
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                // Already here, do nothing or scroll to top
                return true;
            }

            Intent intent = null;

            if (id == R.id.nav_orders) {
                intent = new Intent(Home_Screen.this, orders_screen.class);
            }
            else if (id == R.id.nav_profile) {
                // You can reuse your Farmer_Portfolio or create a new ProfileActivity
                intent = new Intent(Home_Screen.this, profile_screen.class);
            }
            else if (id == R.id.nav_schemes) {
                intent = new Intent(Home_Screen.this, Schemes_screen.class);
            }

            if (intent != null) {
                // This flag prevents creating a massive stack of activities
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                // Optional: overridePendingTransition(0, 0); // Remove animation for smoother feel
            }

            return true;
        });
    }
}