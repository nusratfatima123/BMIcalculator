package com.example.bmi_calculator.bmicalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BMIRecordAdapter extends RecyclerView.Adapter<BMIRecordAdapter.ViewHolder> {
    private List<BMIRecord> bmiRecords;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(BMIRecord record);
        void onItemLongClick(BMIRecord record);
    }

    public BMIRecordAdapter(Context context, List<BMIRecord> bmiRecords, OnItemClickListener listener) {
        this.context = context;
        this.bmiRecords = bmiRecords;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bmi_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BMIRecord record = bmiRecords.get(position);

        holder.tvDate.setText("Date: " + record.getDateTime());
        holder.tvBMIScore.setText(String.format("BMI: %.2f", record.getBmi())); // FIXED: tvBMIScore
        holder.tvWeightHeight.setText(String.format("Weight: %.1f kg | Height: %.1f ft",
                record.getWeight(), record.getHeight()));
        holder.tvCategory.setText("Category: " + record.getCategory());

        // Set text color based on BMI category
        int color = getCategoryColor(record.getCategory());
        holder.tvCategory.setTextColor(color);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(record));
        holder.itemView.setOnLongClickListener(v -> {
            listener.onItemLongClick(record);
            return true;
        });
    }

    private int getCategoryColor(String category) {
        switch (category.toLowerCase()) {
            case "underweight":
                return context.getResources().getColor(android.R.color.holo_blue_dark);
            case "normal weight":
                return context.getResources().getColor(android.R.color.holo_green_dark);
            case "overweight":
                return context.getResources().getColor(android.R.color.holo_orange_dark);
            case "obese":
                return context.getResources().getColor(android.R.color.holo_red_dark);
            default:
                return context.getResources().getColor(android.R.color.black);
        }
    }

    @Override
    public int getItemCount() {
        return bmiRecords.size();
    }

    public void updateData(List<BMIRecord> newRecords) {
        bmiRecords.clear();
        bmiRecords.addAll(newRecords);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvBMIScore, tvWeightHeight, tvCategory; // FIXED: tvBMIScore

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvBMIScore = itemView.findViewById(R.id.tvBMIScore); // FIXED: tvBMIScore
            tvWeightHeight = itemView.findViewById(R.id.tvWeightHeight);
            tvCategory = itemView.findViewById(R.id.tvCategory);
        }
    }
}