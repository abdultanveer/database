package com.example.database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.List;

public class SmsContentProviderActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor>
{

    int LOADER_ID = 007;
    Bundle instructions;
    CursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_content_provider);
        Toast.makeText(this, "sending sms to binsun", Toast.LENGTH_SHORT).show();
        sendAnSms();

        LoaderManager manager = getSupportLoaderManager(); //work manager
        manager.initLoader(LOADER_ID,instructions,this); //hiring

        ListView inboxListView = findViewById(R.id.listviewinbox);
        //uri -- like URL --- it'll unique identify the table http://yahoo.com
        //uri - content://sms/inbox
       /* Uri uriSms = Uri.parse("content://sms/inbox");
        //projection -- columns,   selection -- selecting rows
        Cursor cursor = getContentResolver().query( //queried on main thread
                ContactsContract.Contacts.CONTENT_URI,
                //uriSms,//table name
                null,null,null,null);*/
         adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                null,
                new String[]{
                        //ContactsContract.Contacts.DISPLAY_NAME},
                        "body","address"},
                new int[]{android.R.id.text1,android.R.id.text2},
                        //,android.R.id.text2}
                        0);
        inboxListView.setAdapter(adapter);
        //content resolver -- client app [database]
        //content provider -- server app [sms app]

    }

    private void sendAnSms() {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("5554",null,
                "happy birthday binsun",null,null);
    }

    @NonNull
    @Override /// first day of  a loader[coolie]
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        //first day -- show the loader from which warehouse should he pick up the goods
        Uri uriSms = Uri.parse("content://sms/inbox");//address to the warehouse

        return new CursorLoader(this,uriSms,null,null,
                null,null);
    }

    @Override //loader finishes his job -- pay him
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor loadedCursor) {
        adapter.swapCursor(loadedCursor);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
