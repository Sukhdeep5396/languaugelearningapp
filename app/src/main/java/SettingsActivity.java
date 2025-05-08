package com.harrie.learninglanguageapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private Button btnReset;
    private TextView txtResetFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnReset = findViewById(R.id.btnReset);
        txtResetFeedback = findViewById(R.id.txtResetFeedback);

        btnReset.setOnClickListener(v -> {
            // Future: add real settings reset logic here
            txtResetFeedback.setText(getString(R.string.reset_placeholder));
        });
    }
}
