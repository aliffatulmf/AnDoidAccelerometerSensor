package com.bit.accelerometer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;

public class DatabaseManager {
    private DatabaseHelper dbHelper, dbRHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context ctx) {
        context = ctx;
   }

   public DatabaseManager open() throws SQLDataException {
        dbHelper = new DatabaseHelper(context);
        return this;
   }

   public void close() {
        dbHelper.close();
   }

   public void insert(String x, String y, String z) {
       database = dbHelper.getWritableDatabase();

       ContentValues contentValues = new ContentValues();
       contentValues.put(dbHelper.SENSOR_X, x);
       contentValues.put(dbHelper.SENSOR_Y, y);
       contentValues.put(dbHelper.SENSOR_Z, z);

       database.insert(dbHelper.TABLE_NAME, null, contentValues);
   }

   public Cursor viewAll() {
       database = dbHelper.getReadableDatabase();

       String query = "select * from " + dbRHelper.TABLE_NAME;
       Cursor cursor = database.rawQuery(query, null);

       return cursor;
   }
}
