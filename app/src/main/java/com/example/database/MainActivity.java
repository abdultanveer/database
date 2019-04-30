package com.example.database;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    DbOperations operations;
    EditText dataEditText;
    public static String PREFS_FILENAME = "myprefs";
    public  static int MODE = Activity.MODE_PRIVATE;
    public  static String KEY = "mykey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataEditText = findViewById(R.id.editText);
        /*operations = new DbOperations(this);
        operations.openDb();
        operations.createRow();

        Cursor cursor = operations.readRows();
        ListView listView =  findViewById(R.id.listview);

        CursorAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor,
                new String[]{"title","details"},new int[]{android.R.id.text1,android.R.id.text2},0);
        listView.setAdapter(adapter);*/
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

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    /**
     * this method stores data in a shared preferences
     */
    private void saveData() {
        //get the data from the edittext
        String data = dataEditText.getText().toString();
        //create a shared prefs file
        SharedPreferences preferences = getSharedPreferences(PREFS_FILENAME,MODE);
        //open the file
        SharedPreferences.Editor editor = preferences.edit();
        //write to file
        editor.putString(KEY,data);
       // editor.pu
        //save to file
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreData();
    }

    private  void restoreData(){
        //open file
        SharedPreferences preferences = getSharedPreferences(PREFS_FILENAME,MODE);
        //get the data
       String data = preferences.getString(KEY,"");
        //set the data in edittext
        dataEditText.setText(data);
    }
}
