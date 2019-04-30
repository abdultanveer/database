package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbOperations {
    DbHelper helper;
    SQLiteDatabase database;
    public  DbOperations(Context context){
        helper = new DbHelper(context);
    }

    public void openDb(){
        database = helper.getWritableDatabase();
    }

    public void createRow(){
        ContentValues values = new ContentValues();
        for(int i=0;i<10;i++) {
            values.put("title", "my title"+i);
            values.put("details", "my details"+i);
            database.insert("tasks",null,values);

        }

    }

    public String readRow(){
       Cursor cursor = database.query("tasks",null,null,null
        ,null,null,null);
       cursor.moveToLast();
       int titleColIndex = cursor.getColumnIndexOrThrow("title");
        int detailsColIndex = cursor.getColumnIndexOrThrow("details");

        String title = cursor.getString(titleColIndex);
        String details = cursor.getString(detailsColIndex);

        return title+"\n"+details;

    }
    public Cursor readRows(){
        return database.query("tasks",null,null,null
                ,null,null,null);
    }



    }
