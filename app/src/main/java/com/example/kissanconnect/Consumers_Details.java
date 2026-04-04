package com.example.kissanconnect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class Consumers_Details extends AppCompatActivity {

    private TextInputEditText edtName, edtMobile, edtAddress, edtCity;
    private AutoCompleteTextView stateAutoComplete;
    private MaterialButton btnSaveNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumers_details);

        // 1. Initialize Views
        edtName = findViewById(R.id.edtName);
        edtMobile = findViewById(R.id.edtMobile);
        edtAddress = findViewById(R.id.edtAddress);
        edtCity = findViewById(R.id.edtCity);
        stateAutoComplete = findViewById(R.id.stateAutoComplete);
        btnSaveNext = findViewById(R.id.btnSaveNext);

        // 2. Setup State Dropdown Adapter
        String[] states = {"Maharashtra", "Madhya Pradesh", "Gujarat", "Karnataka", "Punjab", "Delhi", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, states);
        stateAutoComplete.setAdapter(adapter);

        // 3. Button Click Listener
        btnSaveNext.setOnClickListener(v -> {
            if (validateFields()) {
                saveDataToMemory();

                // Navigate to Customer Home Screen
                Intent intent = new Intent(Consumers_Details.this, Consumers_Home_Screen.class);
                startActivity(intent);
                finish(); // Close this activity so user can't go back to the form
            }
        });
    }

    private boolean validateFields() {
        String name = edtName.getText().toString().trim();
        String mobile = edtMobile.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String city = edtCity.getText().toString().trim();
        String state = stateAutoComplete.getText().toString().trim();

        // Check each field
        if (TextUtils.isEmpty(name)) {
            edtName.setError("Name is required");
            edtName.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(mobile)) {
            edtMobile.setError("Mobile number is required");
            edtMobile.requestFocus();
            return false;
        } else if (mobile.length() != 10) {
            edtMobile.setError("Enter a valid 10-digit number");
            edtMobile.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(address)) {
            edtAddress.setError("Address is required");
            edtAddress.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(city)) {
            edtCity.setError("City is required");
            edtCity.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(state)) {
            stateAutoComplete.setError("Please select a state", null);
            Toast.makeText(this, "Please select a state", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void saveDataToMemory() {
        // Using SharedPreferences to store data in app memory
        SharedPreferences sharedPref = getSharedPreferences("ConsumerPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("userName", edtName.getText().toString().trim());
        editor.putString("userMobile", edtMobile.getText().toString().trim());
        editor.putString("userAddress", edtAddress.getText().toString().trim());
        editor.putString("userCity", edtCity.getText().toString().trim());
        editor.putString("userState", stateAutoComplete.getText().toString().trim());

        editor.apply(); // Saves data asynchronously
    }
}