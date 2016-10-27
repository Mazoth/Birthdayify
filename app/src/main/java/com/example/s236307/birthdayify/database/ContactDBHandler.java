package com.example.s236307.birthdayify.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.s236307.birthdayify.Contact;

public class ContactDBHandler extends SQLiteOpenHelper {
    private static ContactDBHandler singleton;

    public static ContactDBHandler getInstance(final Context context) {
        if(singleton == null) {
            singleton = new ContactDBHandler(context);
        }
        return singleton;
    }

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "birthdayify.db";

    private final Context context;

    public ContactDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Contact.CREATE_TABLE);
    }

    public synchronized Contact getContact(final long id) {
        final SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.query(Contact.TABLE_NAME, Contact.COLUMNS, Contact.COL_ID + "IS ?",
                new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor == null || cursor.isAfterLast()) return null;

        Contact item = null;
        if(cursor.moveToFirst()) {
            item = new Contact(cursor);
        }
        cursor.close();
        return item;
    }

    public synchronized int removeContact(final Contact contact) {
        final SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Contact.TABLE_NAME,
                Contact.COL_ID + " IS ?",
                new String[] {Long.toString(contact.id)});
    }

    public synchronized boolean addContact(final Contact contact) {
        int result = 0;
        final SQLiteDatabase db = this.getWritableDatabase();

        //Check to see if this contact already exists
        if(contact.id > -1) {
            result += db.update(Contact.TABLE_NAME, contact.getContent(),
                    Contact.COL_ID + " IS ?",
                    new String[] { String.valueOf(contact.id)});
        }
        // In case the contact was updated correctly, we return true
        if(result > 0) {
            notifyProviderWhenDBChange();
            return true;
        }
        // Or else we insert the new contact into the table
        else {
            final long id = db.insert(Contact.TABLE_NAME, null, contact.getContent());
            if(id > -1) {
                contact.id = id;
                notifyProviderWhenDBChange();
                return true;
            }
        }
        return false;
    }

    private void notifyProviderWhenDBChange() {
        context.getContentResolver().notifyChange(ContactProvider.CONTENT_URI, null, false);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
