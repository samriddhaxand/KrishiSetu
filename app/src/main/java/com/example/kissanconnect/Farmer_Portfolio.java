package com.example.kissanconnect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class Farmer_Portfolio extends AppCompatActivity {

    private TextInputEditText edtExp, edtIncome, edtArea, edtMarket, edtBio;
    private RadioButton radioYes;
    private MaterialButton btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_portfolio);

        // Bind Portfolio Views
        edtExp = findViewById(R.id.edtExperience);
        edtIncome = findViewById(R.id.edtIncome);
        edtArea = findViewById(R.id.edtArea);
        edtMarket = findViewById(R.id.edtMarket);
        edtBio = findViewById(R.id.edtBio);
        radioYes = findViewById(R.id.radioYes);
        btnFinish = findViewById(R.id.btnSubmitPortfolio);

        btnFinish.setOnClickListener(v -> {
            // 1. Get input strings and trim spaces
            String exp = edtExp.getText().toString().trim();
            String income = edtIncome.getText().toString().trim();
            String area = edtArea.getText().toString().trim();
            String market = edtMarket.getText().toString().trim();
            String bio = edtBio.getText().toString().trim(); // Bio stays optional

            // Check if any RadioButton in the group is selected
            // Note: Assuming your RadioGroup ID is rgOrganic
            RadioGroup rgOrganic = findViewById(R.id.rgOrganic);

            // 2. Validation Logic
            if (exp.isEmpty()) {
                edtExp.setError("Experience is required");
                edtExp.requestFocus();
            } else if (income.isEmpty()) {
                edtIncome.setError("Annual income is required");
                edtIncome.requestFocus();
            } else if (area.isEmpty()) {
                edtArea.setError("Land area is required");
                edtArea.requestFocus();
            } else if (market.isEmpty()) {
                edtMarket.setError("Market address is required");
                edtMarket.requestFocus();
            } else if (rgOrganic.getCheckedRadioButtonId() == -1) {
                // No radio button selected
                Toast.makeText(this, "Please select if you use organic methods", Toast.LENGTH_SHORT).show();
            } else {
                // 3. VALIDATION PASSED - Proceed with saving

                // Get data from previous screen
                Bundle bundle = getIntent().getExtras();
                String name = (bundle != null) ? bundle.getString("u_name", "") : "";
                String mobile = (bundle != null) ? bundle.getString("u_mobile", "") : "";

                boolean isOrganic = radioYes.isChecked();

                // Store EVERYTHING permanently
                saveAllData(name, mobile, exp, income, area, market, isOrganic, bio);

                Toast.makeText(this, "Profile Completed!", Toast.LENGTH_LONG).show();

                // 4. OPEN HOME SCREEN AND CLEAR FLOW
                Intent intent = new Intent(Farmer_Portfolio.this, Home_Screen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private void saveAllData(String name, String phone, String exp, String income, String area, String mkt, boolean org, String bio) {
        SharedPreferences pref = getSharedPreferences("FarmerProfile", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("full_name", name);
        editor.putString("phone_number", phone);
        editor.putString("experience", exp);
        editor.putString("annual_income", income);
        editor.putString("land_area", area);
        editor.putString("market_address", mkt);
        editor.putBoolean("is_organic", org);
        editor.putString("biography", bio);

        editor.apply();
    }
}