package com.example.kissanconnect;

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
        cropList.add(new crop("Wheat", "250 kg", "₹22/kg", "Available", "Fresh", R.drawable.crop));
        cropList.add(new crop("Tomatoes", "80 kg", "₹35/kg", "Available", "Very Fresh", R.drawable.tomato));
// ... add others

        CropAdapter adapter = new CropAdapter(cropList);
        recyclerView.setAdapter(adapter);

        name = findViewById(R.id.tvHomeUserName);
        location = findViewById(R.id.tvHomeUserLocation);

        SharedPreferences pref = getSharedPreferences("FarmerProfile", MODE_PRIVATE);
        String farmerName = pref.getString("full_name", "Farmer");
        String farmerCity = pref.getString("u_city", "Location not set");
        name.setText(farmerName);
        location.setText(farmerCity);
    }
}