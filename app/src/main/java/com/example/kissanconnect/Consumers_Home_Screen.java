package com.example.kissanconnect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class Consumers_Home_Screen extends AppCompatActivity {

    private TextView tvWelcomeHeader;
    private RecyclerView rvMarket;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumers_home_screen);

        // 1. Initialize Views
        tvWelcomeHeader = findViewById(R.id.tvWelcomeHeader);
        rvMarket = findViewById(R.id.rvMarket);
        bottomNavigation = findViewById(R.id.bottom_navigation);

        // 2. Set Personalized Welcome Name from Memory
        SharedPreferences prefs = getSharedPreferences("ConsumerPrefs", MODE_PRIVATE);
        String savedName = prefs.getString("userName", "User");
        tvWelcomeHeader.setText("Welcome, " + savedName);

        // 3. Setup RecyclerView Grid
        setupRecyclerView();

        // 4. Handle Navbar Clicks
        setupNavigation();
    }

    private void setupNavigation() {
        // Set the Market item as selected by default
        bottomNavigation.setSelectedItemId(R.id.bottom_navigation);

        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home_cons) {
                // Already on this page, do nothing or scroll to top
                return true;
            } else if (itemId == R.id.nav_longterm) {
                startActivity(new Intent(this, LongtermActivity.class));
                return true;
            } else if (itemId == R.id.nav_order_cons) {
                startActivity(new Intent(this, Consumers_orders.class));
                return true;
            } else if (itemId == R.id.nav_cart) {
                startActivity(new Intent(this, Consumers_cart.class));
                return true;
            }

            return false;
        });
    }

    private void setupRecyclerView() {
        List<Product> list = new ArrayList<>();
        // Add your dummy data here matching the image
        list.add(new Product("Fresh Tomatoes", "Ramesh Kumar", "₹45 / per kg", R.drawable.tomatoes));
        list.add(new Product("Spinach", "Ramesh Kumar", "₹30 / per bunch", R.drawable.spinach));
        list.add(new Product("Grapes", "Nisheeth Pamidi", "₹60 / per kg", R.drawable.grapes));

        rvMarket.setLayoutManager(new GridLayoutManager(this, 2));
        ProductAdapter adapter = new ProductAdapter(list);
        rvMarket.setAdapter(adapter);
    }
}