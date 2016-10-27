package com.example.s236307.birthdayify;


import android.content.ContentValues;
import android.database.Cursor;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Contact {
    public static final String TABLE_NAME = "Contact";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_PHONENUMBER = "phonenumber";
    public static final String COL_BIRTHDAY = "birthday";
    public static final String COL_PERSONALIZEDSMS = "personalsms";

    //An array of Strings intended as a projection, ensuring consistency in the order of columns
    public static final String[] COLUMNS = { COL_ID, COL_NAME, COL_PHONENUMBER, COL_BIRTHDAY };

    // Create table string which is parsed through when the table is created.
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY,"
            + COL_NAME + " TEXT NOT NULL,"
            + COL_PHONENUMBER + " TEXT NOT NULL,"
            + COL_BIRTHDAY + " INTEGER NOT NULL,"
            + COL_PERSONALIZEDSMS + " TEXT DEFAULT '')";

    public long id = -1;
    public String name, phoneNumber;
    public long birthdayInMillis;
    //Constructor for when a contact is to be registered and put into the database
    public Contact () {}

    //Constructor for a Contact that has been retrieved from the database
    public Contact (final Cursor cursor){
        this.id = cursor.getLong(0);
        this.name = cursor.getString(1);
        this.phoneNumber = cursor.getString(2);
        this.birthdayInMillis = cursor.getLong(3);
    }

    public ContentValues getContent() {
        final ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_PHONENUMBER, phoneNumber);
        values.put(COL_BIRTHDAY, birthdayInMillis);
        return values;
    }

    // A method that's called when I wish to display the date in a text format.
    public String displayDateText() {
        SimpleDateFormat sf = new SimpleDateFormat("dd MMM", new Locale("no"));
        return sf.format(birthdayInMillis);
    }
}
