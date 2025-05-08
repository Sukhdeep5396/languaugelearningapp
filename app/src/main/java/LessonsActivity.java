package com.harrie.learninglanguageapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class LessonsActivity extends AppCompatActivity {

    private LinearLayout lessonContainer;
    private LessonDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        // Toolbar setup with back navigation
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("French Lessons");
        }

        // Bottom Navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setSelectedItemId(R.id.nav_home);  // or highlight the current
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (id == R.id.nav_quiz) {
                startActivity(new Intent(this, QuizActivity.class));
                return true;
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }
            return false;
        });

        // Initialize DB
        dbHelper = new LessonDatabaseHelper(this);

        // Load lessons from DB
        lessonContainer = findViewById(R.id.lessonContainer);
        loadLessonsFromDatabase();
    }

    private void loadLessonsFromDatabase() {
        List<Lesson> lessons = dbHelper.getAllLessons();
        for (Lesson lesson : lessons) {
            // Lesson Title
            TextView titleView = new TextView(this);
            titleView.setText(lesson.getTitle());
            titleView.setTextSize(18f);
            titleView.setTextColor(getResources().getColor(R.color.primaryColor));
            titleView.setPadding(0, 24, 0, 8);
            titleView.setTypeface(null, android.graphics.Typeface.BOLD);
            lessonContainer.addView(titleView);

            // Lesson Content
            String[] lines = lesson.getContent().split("\n");
            for (String line : lines) {
                TextView contentView = new TextView(this);
                contentView.setText("• " + line);
                contentView.setTextSize(16f);
                contentView.setPadding(8, 4, 0, 4);
                lessonContainer.addView(contentView);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // go back to previous screen
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
