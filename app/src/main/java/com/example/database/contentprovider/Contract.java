package com.example.database.contentprovider;

import android.net.Uri;

public class Contract {

    private Contract() {}

    public static final String AUTHORITY = "com.android.example.minimalistcontentprovider.provider";
    public static final String CONTENT_PATH = "words"; //table name

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + CONTENT_PATH);

    static final int ALL_ITEMS = -2;
    final
    String WORD_ID = "id";

    static final String SINGLE_RECORD_MIME_TYPE = "vnd.android.cursor.item/vnd.com.example.provider.words";
    static final String MULTIPLE_RECORD_MIME_TYPE = "vnd.android.cursor.dir/vnd.com.example.provider.words";

    

}
