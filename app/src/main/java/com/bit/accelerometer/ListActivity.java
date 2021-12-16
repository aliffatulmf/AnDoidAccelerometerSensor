package com.bit.accelerometer;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    protected DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView lLine = (ListView)findViewById(R.id.logLine);
        ArrayList<String> arrayList = new ArrayList<>();

        dbManager = new DatabaseManager(this);
        try {
            dbManager.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Cursor cursor = dbManager.viewAll();

        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                arrayList.add("ID " + cursor.getString(0) + "\nSensor X = " + cursor.getString(1) + "\nSensor Y = " + cursor.getString(2) + "\nSensor Z = " + cursor.getString(3) + ",\nTime " + cursor.getString(4));
            }
        } else {
            arrayList.add("Empty");
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        lLine.setAdapter(arrayAdapter);
    }
}