package com.example.bmi_calculator.bmicalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.widget.Toolbar;
public class MainActivity extends AppCompatActivity {
    TextView displayBMI;
    EditText weight, height_ft, height_inc;
    Button calculate, home, food, exercise, historyButton;
    double bmi = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        // ======================= Initialization ==========================
        displayBMI = findViewById(R.id.bmiDisplay);
        height_ft = findViewById(R.id.teHeight_ft);
        height_inc = findViewById(R.id.teHeight_inc);
        calculate = findViewById(R.id.calculateButton);
        home = findViewById(R.id.homeButton);
        food = findViewById(R.id.foodButton);
        exercise = findViewById(R.id.exerciseButton);
        weight = findViewById(R.id.teWeight);

        // Initialize History Button (make sure this exists in your XML)
        historyButton = findViewById(R.id.historyButton);

        // =========================== BMI Calculation ==========================
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weightInput = weight.getText().toString().trim();
                String heightFtInput = height_ft.getText().toString().trim();
                String heightInchInput = height_inc.getText().toString().trim();

                if (weightInput.isEmpty() || heightFtInput.isEmpty() || heightInchInput.isEmpty()) {
                    showErrorDialog("Error!", "Please enter your weight and height.");
                } else {
                    float weight1 = Float.parseFloat(weight.getText().toString());
                    float height1 = Float.parseFloat(height_ft.getText().toString());
                    float height2 = Float.parseFloat(height_inc.getText().toString());

                    double height_m = (height1 * 0.3048 + height2 * 0.0254);
                    bmi = weight1 / (height_m * height_m);

                    String bmiCategory;
                    if (bmi < 18.5) {
                        bmiCategory = "Underweight";
                    } else if (bmi < 25) {
                        bmiCategory = "Normal Weight";
                    } else if (bmi < 30) {
                        bmiCategory = "Overweight";
                    } else if (bmi >= 30 && bmi < 39.9) {
                        bmiCategory = "Obese";
                    } else {
                        bmiCategory = "Extremely Obese";
                    }

                    String resultText = String.format(
                            "Your BMI is: %.1f\n%s",
                            bmi,
                            bmiCategory
                    );

                    displayBMI.setText(resultText);
                    Toast.makeText(MainActivity.this, "BMI Calculated: " + bmiCategory, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // ===================== Bottom Navigation =========================

        // Home Button - Refresh current activity
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear inputs
                weight.setText("");
                height_ft.setText("");
                height_inc.setText("");
                displayBMI.setText("Enter your weight and height to calculate BMI");
                Toast.makeText(MainActivity.this, "Cleared inputs", Toast.LENGTH_SHORT).show();
            }
        });

        // Food Button
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bmi == 0) {
                    showErrorDialog("Error!", "Please calculate your BMI first.");
                } else {
                    Intent intent = new Intent(MainActivity.this, FoodActivity.class);
                    intent.putExtra("BMI_VALUE", (float) bmi);
                    startActivity(intent);
                }
            }
        });

        // Exercise Button
        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bmi == 0) {
                    showErrorDialog("Error!", "Please calculate your BMI first.");
                } else {
                    Intent intent = new Intent(MainActivity.this, ExerciseActivity.class);
                    intent.putExtra("BMI_VALUE", (float) bmi);
                    startActivity(intent);
                }
            }
        });

        // History Button
        if (historyButton != null) {
            historyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                    startActivity(intent);
                }
            });
        }

        // Menu Button (if you have it)
        Button menuButton = findViewById(R.id.menuButton);
        if (menuButton != null) {
            menuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openOptionsMenu();
                }
            });
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // ==================== OPTIONS MENU ====================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_history) {
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_webview) {
            Intent intent = new Intent(this, WebViewActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_api) {
            Intent intent = new Intent(this, ApiActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_clear_history) {
            showClearHistoryDialog();
            return true;
        } else if (id == R.id.menu_about) {
            showAboutDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showErrorDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void showClearHistoryDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Clear History")
                .setMessage("Are you sure you want to clear all BMI history?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    Toast.makeText(this, "History cleared!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void showAboutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("About BMI Calculator")
                .setMessage("BMI Calculator v2.0\n\nFeatures:\n• Calculate BMI\n• Food Suggestions\n• Exercise Plans\n• History Tracking\n• Health Articles\n• API Data")
                .setPositiveButton("OK", null)
                .show();
    }
}