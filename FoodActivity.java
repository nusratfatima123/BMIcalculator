package com.example.bmi_calculator.bmicalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FoodActivity extends AppCompatActivity {
    TextView bmiResultText, foodSuggestionText;
    Button home, food, exercise;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        // Initialize UI Components
        bmiResultText = findViewById(R.id.bmiResultText);
        foodSuggestionText = findViewById(R.id.foodSuggestionText);
        home = findViewById(R.id.homeButton);
        food = findViewById(R.id.foodButton);
        exercise = findViewById(R.id.exerciseButton);

        // Get BMI from Intent
        float bmi = getIntent().getFloatExtra("BMI_VALUE", 0.0f);

        // Display BMI result
        bmiResultText.setText("Your BMI: " + String.format("%.2f", bmi));

        // Display Food Suggestions
        foodSuggestionText.setText(getFoodSuggestion(bmi));

        // ===================== Bottom Navigation =========================

        // Home Button (Navigates back to MainActivity)
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(FoodActivity.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });

        // Food Button (Stay on the same screen)
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent foood = new Intent(FoodActivity.this, FoodActivity.class);
                foood.putExtra("BMI_VALUE", bmi);
                startActivity(foood);
            }
        });

        // Exercise Button (Navigate to ExerciseActivity)
        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exerciseIntent = new Intent(FoodActivity.this, ExerciseActivity.class);
                exerciseIntent.putExtra("BMI_VALUE", bmi);
                startActivity(exerciseIntent);
            }
        });
    }

    // Food Suggestions Based on BMI
    // Food Suggestions Based on BMI (English)
    private String getFoodSuggestion(float bmi) {
        if (bmi < 18.5) {
            return "⚠️ You are Underweight.\n\n\n" +
                    "Your daily calorie intake: 2300-3000 calories\n\n\n" +
                    "🥗 **What should be in your diet?**\n" +
                    "• High-calorie foods: Rice, bread, potatoes, sweet potatoes\n" +
                    "• Protein-rich foods: Eggs, meat, fish, lentils, nuts\n" +
                    "• Dairy products: Milk, yogurt, cheese\n" +
                    "• Healthy fats: Avocado, olive oil, ghee\n\n" +
                    "💡 **Recommendation:** Eat 5-6 times a day, you can have protein shakes and nuts.";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "✅ Your weight is Normal (Normal Weight).\n\n\n" +
                    "Your daily calorie intake: 2000-2500 calories\n\n\n" +
                    "🥗 **What should be in your diet?**\n" +
                    "• Moderate carbohydrates: Rice, wheat bread, oats\n" +
                    "• Protein: Fish, chicken, lentils, chickpeas\n" +
                    "• Vegetables and fruits: Carrots, spinach, apples, bananas\n" +
                    "• Healthy fats: Nuts, olive oil\n\n" +
                    "💡 **Recommendation:** Follow a balanced diet, avoid excessive fast food.";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "⚠️ You are Overweight.\n\n\n" +
                    "Your daily calorie intake: 1500-2000 calories\n\n\n" +
                    "🥗 **What should be in your diet?**\n" +
                    "• Low-calorie foods: Green vegetables, carrots, tomatoes\n" +
                    "• Protein: Chicken, fish, chickpeas, lentils\n" +
                    "• Low-fat dairy products: Milk, yogurt\n" +
                    "• Low carbohydrates: Brown rice, wheat bread\n\n" +
                    "💡 **Recommendation:** Avoid fast food and excessive fried foods, walk regularly.";
        } else {
            return "⚠️ You have Obesity.\n\n\n" +
                    "Your daily calorie intake: 1200-1500 calories\n\n\n" +
                    "🥗 **What should be in your diet?**\n" +
                    "• High fiber foods: Vegetables, spinach, raw carrots\n" +
                    "• Protein: Fish, lentils, chicken (not fried)\n" +
                    "• Low-fat dairy products: Skim milk, yogurt\n" +
                    "• Healthy carbohydrates: Brown rice, oats\n\n" +
                    "💡 **Recommendation:** Walk 30 minutes daily, reduce fast food and sweets intake.";
        }
    }
}
