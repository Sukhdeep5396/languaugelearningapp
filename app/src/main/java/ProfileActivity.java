package com.harrie.learninglanguageapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    private EditText editFullName, editEmail, editLanguage;
    private Spinner spinnerLevel;
    private TextView summaryText;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize views
        editFullName = findViewById(R.id.editFullName);
        editEmail = findViewById(R.id.editEmail);
        editLanguage = findViewById(R.id.editLanguage);
        spinnerLevel = findViewById(R.id.spinnerLevel);
        btnSave = findViewById(R.id.btnSave);
        summaryText = findViewById(R.id.summaryText);

        // Set up Spinner (dropdown)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.level_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(adapter);

        // Save button logic
        btnSave.setOnClickListener(v -> {
            String summary = "👤 " + editFullName.getText().toString() + "\n"
                    + "📧 " + editEmail.getText().toString() + "\n"
                    + "🈯 Language: " + editLanguage.getText().toString() + "\n"
                    + "📈 Level: " + spinnerLevel.getSelectedItem().toString();
            summaryText.setText(summary);
        });

        // Bottom navigation handling
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
