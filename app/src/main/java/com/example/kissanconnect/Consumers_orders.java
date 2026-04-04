package com.example.kissanconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class Consumers_orders extends AppCompatActivity {

    private TextView tvActiveOrders, tvTotalSpent;
    private RecyclerView rvOrders;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumers_orders);

        initViews();

        // Page Stats matching your UI design
        tvActiveOrders.setText("7");
        tvTotalSpent.setText("₹4,515");

        setupRecyclerView();
        setupNavigation();
    }

    private void initViews() {
        tvActiveOrders = findViewById(R.id.tvActiveOrders);
        tvTotalSpent = findViewById(R.id.tvTotalSpent);
        rvOrders = findViewById(R.id.rvOrders);
        bottomNavigation = findViewById(R.id.bottom_navigation);
    }

    private void setupNavigation() {
        bottomNavigation.setSelectedItemId(R.id.nav_order_cons);

        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            Intent intent = null;

            if (itemId == R.id.nav_home_cons) {
                intent = new Intent(Consumers_orders.this, Consumers_Home_Screen.class);
            } else if (itemId == R.id.nav_order_cons) {
                return true; // Already here
            } else if (itemId == R.id.nav_cart) {
                // FIXED: Simple Toast implementation.
                // If you want a custom layout, you must use toast.setView(yourLayout)
                Toast.makeText(Consumers_orders.this, "Cart is coming soon!", Toast.LENGTH_SHORT).show();
                return true;
            } else if (itemId == R.id.nav_longterm) {
                intent = new Intent(Consumers_orders.this, LongtermActivity.class);
            }

            if (intent != null) {
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                return true;
            }
            return false;
        });
    }

    private void setupRecyclerView() {
        rvOrders.setLayoutManager(new LinearLayoutManager(this));
        List<Order_for_consumer> orderList = generateDummyOrders();
        ConsOrderAdapter adapter = new ConsOrderAdapter(orderList);
        rvOrders.setAdapter(adapter);
    }

    private List<Order_for_consumer> generateDummyOrders() {
        List<Order_for_consumer> list = new ArrayList<>();
        // Note: false for Tomatoes/Apples, true for Wheat to match your image exactly
        list.add(new Order_for_consumer("4 Apr 2026", "DELIVERED", R.drawable.tomatoes, "Fresh Tomatoes", "Ramesh Kumar", "5 per kg", "₹225", false));
        list.add(new Order_for_consumer("4 Apr 2026", "CONFIRMED", R.drawable.wheat, "Wheat", "Sunita Devi", "10 per kg", "₹280", true));
        list.add(new Order_for_consumer("4 Apr 2026", "IN PROGRESS", R.drawable.grapes, "Grapes", "Gopal Singh", "3 per kg", "₹190", false));
        return list;
    }
}