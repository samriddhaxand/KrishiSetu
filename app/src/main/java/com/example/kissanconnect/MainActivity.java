package com.example.kissanconnect; // Change this to your actual package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    // Declare the button
    private MaterialButton btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure this matches your XML file name

        // 1. Find the button by the ID you set in the XML
        btnNext = findViewById(R.id.next);

        // 2. Set the click listener to handle the "Next" action
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 3. Create the Intent (Current Context -> Target Class)
                Intent intent = new Intent(MainActivity.this, OpeningActivity2.class);

                // 4. Start the next activity
                startActivity(intent);

                // 5. Optional: Add a smooth fade transition
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                // 6. Finish this activity so the user can't return to it via the back button
                finish();
            }
        });
    }
}