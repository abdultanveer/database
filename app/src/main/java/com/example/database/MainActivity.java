package com.example.database;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    DbOperations operations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operations = new DbOperations(this);
        operations.openDb();
       // operations.createRow();

        Cursor cursor = operations.readRows();
        ListView listView =  findViewById(R.id.listview);

        CursorAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor,
                new String[]{"title","details"},new int[]{android.R.id.text1,android.R.id.text2},0);
        listView.setAdapter(adapter);
    }

    public void clickHandler(View view) {
        switch (view.getId()){
            case R.id.buttonget:
                TextView textView = findViewById(R.id.textViewres);
                textView.setText(operations.readRow());
                break;
            case R.id.buttonsave:
                operations.createRow();
                break;
        }
    }
}
