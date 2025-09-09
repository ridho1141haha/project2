package com.latihan.project2;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPhone;
    private RadioButton rbMobile, rbRumah, rbKantor;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editName);
        editTextEmail = findViewById(R.id.editEmail);
        editTextPhone = findViewById(R.id.editPhone);
        rbMobile      = findViewById(R.id.radioButton1);
        rbRumah       = findViewById(R.id.radioButton2);
        rbKantor      = findViewById(R.id.radioButton3);
        btnSubmit     = findViewById(R.id.btnSubmit);

        if (!rbMobile.isChecked() && !rbRumah.isChecked() && !rbKantor.isChecked()) {
            rbMobile.setChecked(true);
        }

        btnSubmit.setOnClickListener(v -> handleSubmit());
    }

    private void handleSubmit() {
        hideKeyboard();

        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();

        if (name.isEmpty()) {
            editTextName.setError("Nama wajib diisi");
            editTextName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email wajib diisi");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Email tidak valid");
            editTextEmail.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            editTextPhone.setError("Nomor wajib diisi");
            editTextPhone.requestFocus();
            return;
        }
        if (!Patterns.PHONE.matcher(phone).matches() || phone.replaceAll("\\D", "").length() < 8) {
            editTextPhone.setError("Nomor tidak valid");
            editTextPhone.requestFocus();
            return;
        }

        String tipe;
        if (rbMobile.isChecked()) {
            tipe = "Nomor Pribadi";
        } else if (rbRumah.isChecked()) {
            tipe = "Telp Rumah";
        } else if (rbKantor.isChecked()) {
            tipe = "Telp Kantor";
        } else {
            tipe = "Tidak dipilih";
        }

        // Show "Submit berhasil" Toast
        Toast.makeText(this, "Submit berhasil", Toast.LENGTH_SHORT).show();

        // Create Intent and pass data
        Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
        intent.putExtra("NAME", name);
        intent.putExtra("EMAIL", email);
        intent.putExtra("PHONE", phone);
        intent.putExtra("PHONE_TYPE", tipe);
        startActivity(intent);
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}