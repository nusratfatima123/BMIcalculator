package com.example.bmi_calculator.bmicalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ExerciseActivity extends AppCompatActivity {
    TextView bmiResultText, exerciseSuggestionText;
    Button home, food, exercise;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        // Initialize UI Components
        bmiResultText = findViewById(R.id.bmiResultText);
        exerciseSuggestionText = findViewById(R.id.exerciseSuggestionText);
        home = findViewById(R.id.homeButton);
        food = findViewById(R.id.foodButton);
        exercise = findViewById(R.id.exerciseButton);

        // Get BMI from Intent
        float bmi = getIntent().getFloatExtra("BMI_VALUE", 0.0f);

        // Display BMI result
        bmiResultText.setText("Your BMI: " + String.format("%.2f", bmi));

        // Display Exercise Suggestions
        exerciseSuggestionText.setText(getExerciseSuggestion(bmi));

        // ===================== Bottom Navigation =========================

        // Home Button (Navigates back to MainActivity)
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(ExerciseActivity.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });

        // Food Button (Navigate to FoodActivity)
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent foodIntent = new Intent(ExerciseActivity.this, FoodActivity.class);
                foodIntent.putExtra("BMI_VALUE", bmi);
                startActivity(foodIntent);
            }
        });

        // Exercise Button (Stay on the same screen)
        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exercise = new Intent(ExerciseActivity.this, ExerciseActivity.class);
                exercise.putExtra("BMI_VALUE", bmi);
                startActivity(exercise);
            }
        });
    }

    // Exercise Suggestions Based on BMI
    // Exercise Suggestions Based on BMI (English)
    private String getExerciseSuggestion(float bmi) {
        if (bmi < 18.5) {
            return "⚠️ You are Underweight.\n\n" +
                    "🏋️ **Exercises for you:**\n" +
                    "• Strength training for weight gain (30 minutes/day)\n" +
                    "• Squats, push-ups, weight lifting\n" +
                    "• 15-20 minutes yoga daily\n\n" +
                    "🔥 **Recommended calorie burn:** 150-200 calories/session\n\n" +
                    "💡 **Recommendation:** Don't expend too much energy, focus on muscle building.";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "✅ Your weight is Normal (Normal Weight).\n\n" +
                    "🏋️ **Exercises for you:**\n" +
                    "• Balanced cardio and strength training (30-45 minutes/day)\n" +
                    "• Running, cycling, swimming\n" +
                    "• Yoga or stretching\n\n" +
                    "🔥 **Recommended calorie burn:** 200-300 calories/session\n\n" +
                    "💡 **Recommendation:** Do light to moderate exercise daily to maintain fitness.";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "⚠️ You are Overweight.\n\n" +
                    "🏋️ Exercises for you:\n" +
                    "• Cardio exercises for fat loss (40-60 minutes/day)\n" +
                    "• Walking, running, cycling\n" +
                    "• Light weight lifting\n\n" +
                    "🔥 **Recommended calorie burn:** 300-400 calories/session\n\n" +
                    "💡 **Recommendation:** Exercise regularly, avoid excessive fast food.";
        } else {
            return "⚠️ You have Obesity.\n\n" +
                    "🏋️ Exercises for you:\n" +
                    "• Start with cardio and strength training gradually (45-60 minutes/day)\n" +
                    "• Walk 30 minutes daily\n" +
                    "• Swimming, stationary cycling\n\n" +
                    "🔥 **Recommended calorie burn:** 400-500 calories/session\n\n" +
                    "💡 **Recommendation:** Reduce weight gradually, exercise regularly.";
        }
    }
}
