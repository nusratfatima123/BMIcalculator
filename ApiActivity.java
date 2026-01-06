package com.example.bmi_calculator.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
public class ApiActivity extends AppCompatActivity {
    private TextView tvApiData;
    private Button btnFetchApi, homeButton, foodButton, exerciseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        tvApiData = findViewById(R.id.tvApiData);
        btnFetchApi = findViewById(R.id.btnFetchApi);
        homeButton = findViewById(R.id.homeButton);
        foodButton = findViewById(R.id.foodButton);
        exerciseButton = findViewById(R.id.exerciseButton);

        // Show initial mock data
        showMockApiData();

        // Fetch API Button
        btnFetchApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchApiData();
            }
        });

        // Navigation Buttons
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to FoodActivity
                Intent intent = new Intent(ApiActivity.this, FoodActivity.class);
                startActivity(intent);
            }
        });

        exerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ExerciseActivity
                Intent intent = new Intent(ApiActivity.this, ExerciseActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showMockApiData() {
        String mockData = "📊 **Health & Fitness API Data**\n\n" +
                "✅ **Daily Recommendations:**\n" +
                "• Calories: 2000-2500 kcal\n" +
                "• Water: 2-3 liters\n" +
                "• Steps: 10,000 daily\n" +
                "• Sleep: 7-9 hours\n" +
                "• Exercise: 150 mins/week\n\n" +
                "📈 **Nutrition Data:**\n" +
                "• Protein: 50-60g daily\n" +
                "• Carbs: 225-325g daily\n" +
                "• Fat: 44-78g daily\n" +
                "• Fiber: 25-30g daily\n\n" +
                "🏃 **Exercise Data:**\n" +
                "• Avg. heart rate: 120-150 bpm\n" +
                "• Calories burned: 300-500/session\n" +
                "• Recovery time: 24-48 hours\n\n" +
                "📱 **Source:** Health API v1.0";

        tvApiData.setText(mockData);
    }

    private void fetchApiData() {
        // Simulate API call
        Toast.makeText(this, "Fetching API data...", Toast.LENGTH_SHORT).show();

        // Update with new data after delay
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String updatedData = "🔄 **Live API Data (Updated)**\n\n" +
                        "✅ **Real-time Stats:**\n" +
                        "• Active users: 1,234,567\n" +
                        "• Avg BMI: 24.3\n" +
                        "• Success rate: 89%\n" +
                        "• API response: 200ms\n\n" +
                        "📊 **Today's Stats:**\n" +
                        "• BMI calculated: 12,345\n" +
                        "• Food plans: 8,901\n" +
                        "• Exercise logs: 23,456\n\n" +
                        "🌐 **API Status:** Online\n" +
                        "📅 **Last Updated:** " + new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date());

                tvApiData.setText(updatedData);
                Toast.makeText(ApiActivity.this, "API data refreshed!", Toast.LENGTH_SHORT).show();
            }
        }, 1500);
    }

    // Add this import at the top if missing:
    // import android.content.Intent;
}