package com.harrie.learninglanguageapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    private EditText editFullName, editUsername, editEmail, editPhone, editLanguage, editLevel;
    private TextView summaryText;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize UI elements
        editFullName = findViewById(R.id.editFullName);
        editUsername = findViewById(R.id.editUsername);
        editEmail = findViewById(R.id.editEmail);
        editPhone = findViewById(R.id.editPhone);
        editLanguage = findViewById(R.id.editLanguage);
        editLevel = findViewById(R.id.editLevel);
        btnSave = findViewById(R.id.btnSave);
        summaryText = findViewById(R.id.summaryText);

        // Save button logic
        btnSave.setOnClickListener(v -> {
            String summary = "Name: " + editFullName.getText().toString() + "\n"
                    + "Username: " + editUsername.getText().toString() + "\n"
                    + "Email: " + editEmail.getText().toString() + "\n"
                    + "Phone: " + editPhone.getText().toString() + "\n"
                    + "Language: " + editLanguage.getText().toString() + "\n"
                    + "Level: " + editLevel.getText().toString();

            summaryText.setText(summary);
        });

        // Bottom navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setSelectedItemId(R.id.nav_profile);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (id == R.id.nav_quiz) {
                startActivity(new Intent(this, QuizActivity.class));
                return true;
            }
            return false;
        });
    }
}
