// DatabaseHelper.java
package com.example.bmi_calculator.bmicalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bmi_history.db";
    private static final int DATABASE_VERSION = 1;
    
    private static final String TABLE_BMI = "bmi_history";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_WEIGHT = "weight";
    private static final String COLUMN_HEIGHT = "height";
    private static final String COLUMN_BMI = "bmi";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_DATE = "date_time";
    
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BMI_TABLE = "CREATE TABLE " + TABLE_BMI + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_WEIGHT + " REAL,"
                + COLUMN_HEIGHT + " REAL,"
                + COLUMN_BMI + " REAL,"
                + COLUMN_CATEGORY + " TEXT,"
                + COLUMN_DATE + " TEXT"
                + ")";
        db.execSQL(CREATE_BMI_TABLE);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BMI);
        onCreate(db);
    }
    
    public long addBMIRecord(BMIRecord record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_WEIGHT, record.getWeight());
        values.put(COLUMN_HEIGHT, record.getHeight());
        values.put(COLUMN_BMI, record.getBmi());
        values.put(COLUMN_CATEGORY, record.getCategory());
        values.put(COLUMN_DATE, record.getDateTime());
        
        long id = db.insert(TABLE_BMI, null, values);
        db.close();
        return id;
    }
    
    public List<BMIRecord> getAllBMIRecords() {
        List<BMIRecord> records = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_BMI + " ORDER BY " + COLUMN_DATE + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
        if (cursor.moveToFirst()) {
            do {
                BMIRecord record = new BMIRecord();
                record.setId(cursor.getInt(0));
                record.setWeight(cursor.getDouble(1));
                record.setHeight(cursor.getDouble(2));
                record.setBmi(cursor.getDouble(3));
                record.setCategory(cursor.getString(4));
                record.setDateTime(cursor.getString(5));
                records.add(record);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return records;
    }
    
    public void deleteBMIRecord(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BMI, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
    
    public void clearAllHistory() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BMI, null, null);
        db.close();
    }
}