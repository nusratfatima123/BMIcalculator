// HistoryActivity.java
package com.example.bmi_calculator.bmicalculator;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.view.View;  // Add this import

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BMIRecordAdapter adapter;
    private DatabaseHelper dbHelper;
    private TextView tvEmpty;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        
        dbHelper = new DatabaseHelper(this);
        
        recyclerView = findViewById(R.id.recyclerView);
        tvEmpty = findViewById(R.id.tvEmpty);
        
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        loadHistory();
    }
    
    private void loadHistory() {
        List<BMIRecord> records = dbHelper.getAllBMIRecords();
        
        if (records.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            
            adapter = new BMIRecordAdapter(this, records, new BMIRecordAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BMIRecord record) {
                    // Show record details
                    showRecordDetails(record);
                }
                
                @Override
                public void onItemLongClick(BMIRecord record) {
                    // Delete record on long press
                    showDeleteDialog(record);
                }
            });
            
            recyclerView.setAdapter(adapter);
        }
    }
    
    private void showRecordDetails(BMIRecord record) {
        String details = String.format(
                "Date: %s\n\nWeight: %.1f kg\nHeight: %.1f ft\nBMI: %.2f\nCategory: %s",
                record.getDateTime(),
                record.getWeight(),
                record.getHeight(),
                record.getBmi(),
                record.getCategory()
        );
        
        new AlertDialog.Builder(this)
                .setTitle("BMI Record Details")
                .setMessage(details)
                .setPositiveButton("OK", null)
                .show();
    }
    
    private void showDeleteDialog(BMIRecord record) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Record")
                .setMessage("Delete this BMI record?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    dbHelper.deleteBMIRecord(record.getId());
                    loadHistory(); // Refresh list
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}