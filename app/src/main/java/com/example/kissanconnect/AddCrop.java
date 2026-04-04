package com.example.kissanconnect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddCrop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_crop);

        Button cancel = findViewById(R.id.back);

        cancel.setOnClickListener(v -> {
            // Create an Intent to start the AddCrop activity
            Intent intent = new Intent(AddCrop.this,Home_Screen.class);
            startActivity(intent);
        });
    }
}
