// BMIRecord.java
package com.example.bmi_calculator.bmicalculator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BMIRecord {
    private int id;
    private double weight;
    private double height;
    private double bmi;
    private String category;
    private String dateTime;
    
    public BMIRecord() {}
    
    public BMIRecord(double weight, double height, double bmi, String category) {
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        this.category = category;
        this.dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    
    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }
    
    public double getBmi() { return bmi; }
    public void setBmi(double bmi) { this.bmi = bmi; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public String getDateTime() { return dateTime; }
    public void setDateTime(String dateTime) { this.dateTime = dateTime; }
}