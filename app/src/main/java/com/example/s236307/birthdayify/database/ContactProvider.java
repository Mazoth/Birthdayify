package com.example.s236307.birthdayify.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.s236307.birthdayify.Contact;


public class ContactProvider extends ContentProvider {
    public static final String PROVIDER = "com.example.s236307.birrthdayify.provider";
    public static final String CONTACTS = "content://" + PROVIDER + "/contact";
    public static final Uri CONTENT_URI = Uri.parse(CONTACTS);
    public static final String CONTACT = CONTACTS + "/";

    public ContactProvider() {}

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        if(CONTENT_URI.equals(uri)){
            cursor = ContactDBHandler
                    .getInstance(getContext())
                    .getReadableDatabase()
                    .query(Contact.TABLE_NAME, Contact.COLUMNS, null, null, null, null, null, null);
        } else if (uri.toString().startsWith(CONTACT)) {
            final long id = Long.parseLong(uri.getLastPathSegment());
            cursor = ContactDBHandler
                    .getInstance(getContext())
                    .getReadableDatabase()
                    .query(Contact.TABLE_NAME, Contact.COLUMNS,
                            Contact.COL_ID + " IS ?",
                            new String[] { String.valueOf(id)}, null, null, null, null);
        }
        return cursor;
    }

    //The methods below are required when you extend ContentProvider, but I have no use
    //for these particular methods. All of these are currently handled by my ContactDBHandler class

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
