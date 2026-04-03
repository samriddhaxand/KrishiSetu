package com.example.kissanconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;

public class ChooseYourType extends AppCompatActivity {

    private MaterialCardView cardFarmer, cardConsumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_your_type); // Make sure this matches your XML name

        cardFarmer = findViewById(R.id.cardFarmer);
        cardConsumer = findViewById(R.id.cardConsumer);

        cardFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseYourType.this, Farmers_Details.class);
                startActivity(intent);
            }
        });

        cardConsumer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseYourType.this, Consumers_Details.class);
                startActivity(intent);
            }
        });
    }
}