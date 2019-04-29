package com.example.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    DbOperations operations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        operations = new DbOperations(this);
        operations.openDb();
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
