package com.example.kissanconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class LongtermActivity extends AppCompatActivity {

    private RecyclerView rvLongTerm;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longterm);

        initViews();
        setupRecyclerView();
        setupNavigation();
//        addCartBadge(16);
    }

    private void initViews() {
        rvLongTerm = findViewById(R.id.rvLongTermProducts);
        // Ensure this ID matches the BottomNavigationView inside nav_bottom_container.xml
        bottomNavigation = findViewById(R.id.bottom_navigation);

        if (bottomNavigation == null) {
            throw new RuntimeException("Error: BottomNavigationView not found. Check IDs in nav_bottom_container.xml.");
        }
    }

    private void setupRecyclerView() {
        List<LongTermProduct> list = new ArrayList<>();
        list.add(new LongTermProduct("Fresh Tomatoes", "Ramesh Kumar", "₹45 / per kg", "10% OFF", R.drawable.tomatoes));
        list.add(new LongTermProduct("Spinach", "Ramesh Kumar", "₹30 / per bunch", "8% OFF", R.drawable.spinach));
        list.add(new LongTermProduct("Potatoes", "Ramesh Kumar", "₹25 / per kg", "12% OFF", R.drawable.potatoes));
        list.add(new LongTermProduct("Oranges", "Ramesh Kumar", "₹35 / per kg", "10% OFF", R.drawable.oranges));

        rvLongTerm.setLayoutManager(new LinearLayoutManager(this));
        LongTermAdapter adapter = new LongTermAdapter(list);
        rvLongTerm.setAdapter(adapter);
    }

    private void setupNavigation() {
        // FIX: You must use the ID of the MENU ITEM from bottom_nav_menu.xml
        // NOT the navbarContainer (which is a CardView).
        bottomNavigation.setSelectedItemId(R.id.nav_longterm);

        bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home_cons) {
                Intent intent = new Intent(this, Consumers_Home_Screen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                return true;
            } else if (id == R.id.nav_order_cons) {
                Intent intent = new Intent(this, Consumers_orders.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                return true;
            } else if (id == R.id.nav_longterm) {
                return true; // Already here
            } else if (id == R.id.nav_cart) {
                Toast.makeText(this, "Cart is coming soon!", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
    }

    private void addCartBadge(int count) {
        if (bottomNavigation != null) {
            var badge = bottomNavigation.getOrCreateBadge(R.id.nav_cart);
            badge.setVisible(true);
            badge.setNumber(count);
        }
    }
}