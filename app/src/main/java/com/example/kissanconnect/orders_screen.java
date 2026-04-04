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
import java.util.Arrays;
import java.util.List;

public class orders_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_orders_screen);

        RecyclerView rvOrders = findViewById(R.id.rvOrders);
        rvOrders.setLayoutManager(new LinearLayoutManager(this));

        // 1. Fixed 'Order' capitalization
        List<order> orders = new ArrayList<>();

        // 2. Ensure your Order constructor matches these arguments
        orders.add(new order("Suresh Mehta", "Mumbai", "New", "#ORD2041", "₹775", Arrays.asList("Tomatoes", "Onions")));
        orders.add(new order("Nisheeth Pamidi", "Vijaywada", "New", "#ORD2091", "5000", Arrays.asList("Wheat", "Potatoes")));
        orders.add(new order("Srajal Baghel", "Bhopal", "New", "#ORD2000", "1335", Arrays.asList("Grapes", "Oranges")));

        // 3. Fixed the setAdapter call (using the variable 'adapter' not the class name)
        OrderAdapter adapter = new OrderAdapter(this, orders);
        rvOrders.setAdapter(adapter);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_orders);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            Intent intent = null;

            if (id == R.id.nav_home) {
                intent = new Intent(orders_screen.this, Home_Screen.class);
            }
            else if (id == R.id.nav_orders) {
                return true;
            }
            else if (id == R.id.nav_profile) {
                intent = new Intent(orders_screen.this, profile_screen.class);
            }
            else if (id == R.id.nav_schemes) {
                intent = new Intent(orders_screen.this, Schemes_screen.class);
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