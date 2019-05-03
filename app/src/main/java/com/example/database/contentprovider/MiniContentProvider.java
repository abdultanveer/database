package com.example.database.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.database.DbHelper;
import com.example.database.R;

public class MiniContentProvider extends ContentProvider {
    public String[] mData;
    public static String TAG = MiniContentProvider.class.getSimpleName();
    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    DbHelper dbHelper;
    SQLiteDatabase database;
    /*private void initializeUriMatching(){
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.CONTENT_PATH + "/#", 1);
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.CONTENT_PATH, 0);
    }*/
    @Override
    public boolean onCreate() {
        /*initializeUriMatching();
        Context context = getContext();
        mData = context.getResources().getStringArray(R.array.words);
        //Log.e(TAG, "Not implemented: update uri: " + uri.toString());*/
        dbHelper = new DbHelper(getContext());
        database = dbHelper.getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query( Uri uri,  String[] projection,  String selection,  String[] selectionArgs,  String sortOrder) {
        return database.query("tasks",null,null,null,null,null,null);
    }

    
    @Override
    public String getType( Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case 0:
                return Contract.MULTIPLE_RECORD_MIME_TYPE;
            case 1:
                return Contract.SINGLE_RECORD_MIME_TYPE;
            default:
// Alternatively, throw an exception.
                return null;
        }
    }

    
    @Override
    public Uri insert( Uri uri,  ContentValues values) {
        database.insert("tasks",null,values);
        return uri;
    }

    @Override
    public int delete( Uri uri,  String selection,  String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update( Uri uri,  ContentValues values,  String selection,  String[] selectionArgs) {
        return 0;
    }
}
