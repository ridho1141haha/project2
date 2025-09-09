package com.latihan.project2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar; // Import Toolbar

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarDisplay);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Show back button
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        TextView tvName = findViewById(R.id.tvName);
        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvPhone = findViewById(R.id.tvPhone);
        TextView tvPhoneType = findViewById(R.id.tvPhoneType);
        Button btnBack = findViewById(R.id.btnBack); // Find the back button

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("NAME");
            String email = extras.getString("EMAIL");
            String phone = extras.getString("PHONE");
            String phoneType = extras.getString("PHONE_TYPE");

            tvName.setText("Nama: " + name);
            tvEmail.setText("Email: " + email);
            tvPhone.setText("Nomor Telepon: " + phone);
            tvPhoneType.setText("Jenis Nomor: " + phoneType);
        }

        // Set OnClickListener for the back button
        btnBack.setOnClickListener(v -> finish()); // finish() will go back to the previous activity
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Handle back button click on Toolbar
        return true;
    }
}