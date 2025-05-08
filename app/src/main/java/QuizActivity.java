package com.harrie.learninglanguageapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView questionText, scoreText;
    private RadioGroup optionsGroup;
    private RadioButton option1, option2, option3;
    private Button btnNext;

    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Bottom Navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setSelectedItemId(R.id.nav_quiz);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }
            return false;
        });

        // UI elements
        questionText = findViewById(R.id.questionText);
        scoreText = findViewById(R.id.scoreText);
        optionsGroup = findViewById(R.id.optionsGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        btnNext = findViewById(R.id.btnNext);

        setupQuestions();
        displayQuestion();

        btnNext.setOnClickListener(v -> {
            int selectedId = optionsGroup.getCheckedRadioButtonId();
            if (selectedId != -1) {
                int selectedIndex = getSelectedIndex(selectedId);
                if (selectedIndex == questionList.get(currentQuestionIndex).getCorrectIndex()) {
                    score++;
                }

                currentQuestionIndex++;
                if (currentQuestionIndex < questionList.size()) {
                    displayQuestion();
                } else {
                    showScore();
                }
            }
        });
    }

    private void setupQuestions() {
        questionList = new ArrayList<>();
        questionList.add(new Question("What is 'Hello' in French?", new String[]{"Hola", "Bonjour", "Ciao"}, 1));
        questionList.add(new Question("What is 'Thank you' in French?", new String[]{"Merci", "Gracias", "Danke"}, 0));
        questionList.add(new Question("What is 'Monday' in French?", new String[]{"Martes", "Montag", "Lundi"}, 2));
        questionList.add(new Question("What is 'Good night' in French?", new String[]{"Bonne nuit", "Buonanotte", "Buenas noches"}, 0));
    }

    private void displayQuestion() {
        Question q = questionList.get(currentQuestionIndex);
        questionText.setText(q.getQuestion());
        option1.setText(q.getOptions()[0]);
        option2.setText(q.getOptions()[1]);
        option3.setText(q.getOptions()[2]);
        optionsGroup.clearCheck();
    }

    private void showScore() {
        questionText.setText("Quiz Completed!");
        optionsGroup.setVisibility(RadioGroup.GONE);
        btnNext.setEnabled(false);
        scoreText.setText("You scored " + score + " out of " + questionList.size());
    }

    private int getSelectedIndex(int id) {
        if (id == R.id.option1) return 0;
        else if (id == R.id.option2) return 1;
        else return 2;
    }
}
