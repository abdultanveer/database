package com.example.database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.List;

public class SmsContentProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_content_provider);

        ListView inboxListView = findViewById(R.id.listviewinbox);
        //uri -- like URL --- it'll unique identify the table http://yahoo.com
        //uri - content://sms/inbox
        Uri uriSms = Uri.parse("content://sms/inbox");
        //projection -- columns,   selection -- selecting rows
        Cursor cursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                //uriSms,//table name
                null,null,null,null);
        CursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                cursor,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                        //"body","address"},
                new int[]{android.R.id.text1},
                        //,android.R.id.text2}
                        0);
        inboxListView.setAdapter(adapter);
        //content resolver -- client app [database]
        //content provider -- server app [sms app]

    }
}
