package com.example.kissanconnect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

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

        // 2. Set Personalized Welcome Name from SharedPreferences
        // Note: Using "ConsumerPrefs" to match your registration/login logic
        SharedPreferences prefs = getSharedPreferences("ConsumerPrefs", MODE_PRIVATE);
        String savedName = prefs.getString("userName", "User");
        tvWelcomeHeader.setText("Welcome, " + savedName);

        // 3. Setup RecyclerView Grid
        setupRecyclerView();

        // 4. Handle Navbar Clicks
        setupNavigation();
    }

    private void setupNavigation() {
        // FIX: Set the Home item as selected by default using the MENU item ID
        bottomNavigation.setSelectedItemId(R.id.nav_home_cons);

        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home_cons) {
                // Already on this page
                return true;
            } else if (itemId == R.id.nav_longterm) {
                Intent intent = new Intent(Consumers_Home_Screen.this, LongtermActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.nav_order_cons) {
                Intent intent = new Intent(this, Consumers_orders.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.nav_cart) {
                // FIX: Standard Toast implementation
                Toast toast = Toast.makeText(this, "Cart is coming soon!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0); // Centers it on screen
                toast.show();
                return true;
            }

            return false;
        });
    }

    private void setupRecyclerView() {
        List<Product> list = new ArrayList<>();
        // Matching the items seen in your Market Image
        list.add(new Product("Fresh Tomatoes", "Ramesh Kumar", "₹45 / per kg", R.drawable.tomatoes));
        list.add(new Product("Spinach", "Ramesh Kumar", "₹30 / per bunch", R.drawable.spinach));
        list.add(new Product("Grapes", "Nisheeth Pamidi", "₹60 / per kg", R.drawable.grapes));
        // Add more products to fill the grid as needed

        // Span count 2 for the grid layout shown in the UI
        rvMarket.setLayoutManager(new GridLayoutManager(this, 2));
        ProductAdapter adapter = new ProductAdapter(list);
        rvMarket.setAdapter(adapter);
    }
}