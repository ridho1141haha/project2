package com.latihan.project2;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPhone;
    private RadioButton rbMobile, rbRumah, rbKantor;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPhone = findViewById(R.id.editText1);
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

        String phone = editTextPhone.getText().toString().trim();

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

        String msg = "Nomor: " + phone + " (" + tipe + ")";
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
