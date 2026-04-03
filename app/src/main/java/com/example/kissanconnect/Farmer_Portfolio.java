package com.example.kissanconnect;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
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
            // 1. Get data from previous screen (Farmers_Details)
            Bundle bundle = getIntent().getExtras();
            String name = bundle.getString("u_name");
            String mobile = bundle.getString("u_mobile");

            // 2. Get data from current screen
            String exp = edtExp.getText().toString();
            String market = edtMarket.getText().toString();
            String bio = edtBio.getText().toString();
            boolean isOrganic = radioYes.isChecked();

            // 3. Store EVERYTHING permanently on the phone
            saveAllData(name, mobile, exp, market, isOrganic, bio);

            Toast.makeText(this, "Profile Completed!", Toast.LENGTH_LONG).show();
        });
    }

    private void saveAllData(String name, String phone, String exp, String mkt, boolean org, String bio) {
        SharedPreferences pref = getSharedPreferences("FarmerProfile", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("full_name", name);
        editor.putString("phone_number", phone);
        editor.putString("experience", exp);
        editor.putString("market_address", mkt);
        editor.putBoolean("is_organic", org);
        editor.putString("biography", bio);

        editor.apply(); // Data is now saved!
    }
}