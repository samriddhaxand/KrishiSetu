package com.example.kissanconnect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class Farmers_Details extends AppCompatActivity {

    // Declare variables
    private TextInputEditText edtName, edtMobile, edtPassword, edtCity;
    private AutoCompleteTextView productDropdown;
    private MaterialButton btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmers_details);

        // 1. Bind Views (Ensure these IDs match your XML exactly)
        edtName = findViewById(R.id.edtName);
        edtMobile = findViewById(R.id.edtMobile);
        edtPassword = findViewById(R.id.edtPassword);
        edtCity = findViewById(R.id.edtCity);
        productDropdown = findViewById(R.id.productAutoComplete);
        btnSave = findViewById(R.id.btnSave);

        // 2. Fix the Dropdown Crash (Set the Adapter)
        String[] items = {"Vegetables", "Fruits", "Grains", "Dairy", "Spices"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        productDropdown.setAdapter(adapter);

        // 3. Handle Save and Next
        btnSave.setOnClickListener(v -> {
            // 1. Collect data and trim spaces
            String name = edtName.getText().toString().trim();
            String mobile = edtMobile.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();
            String city = edtCity.getText().toString().trim();
            String product = productDropdown.getText().toString().trim();

            // 2. Validation Check
            if (name.isEmpty()) {
                edtName.setError("Name is required");
                edtName.requestFocus();
            } else if (mobile.isEmpty()) {
                edtMobile.setError("Mobile number is required");
                edtMobile.requestFocus();
            } else if (mobile.length() < 10) {
                edtMobile.setError("Enter a valid 10-digit number");
                edtMobile.requestFocus();
            } else if (password.isEmpty()) {
                edtPassword.setError("Password is required");
                edtPassword.requestFocus();
            } else if (city.isEmpty()) {
                edtCity.setError("City is required");
                edtCity.requestFocus();
            } else if (product.isEmpty()) {
                productDropdown.setError("Please select a product category");
                productDropdown.requestFocus();
            } else {
                // 3. All fields are filled! Proceed to next activity
                Intent intent = new Intent(Farmers_Details.this, Farmer_Portfolio.class);

                intent.putExtra("u_name", name);
                intent.putExtra("u_mobile", mobile);
                intent.putExtra("u_pass", password);
                intent.putExtra("u_city", city);
                intent.putExtra("u_product", product);

                startActivity(intent);
            }
        });
    }
}