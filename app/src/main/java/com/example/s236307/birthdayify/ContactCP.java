package com.example.s236307.birthdayify;


import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.util.Log;

public class ContactCP extends ContentProvider {
    public static final String PHONENUMBER = "phoneNumber";
    public static final String TAG = "DbHelper";
    public static final String DB_NAME = "birthdayify.db";
    public static final String TABLE = "contacts";
    public static final String _ID = BaseColumns._ID;
    public static final String FIRSTNAME = "firstName";
    public static final String LASTNAME = "lastName";
    public static final String BIRTHDAY = "birthday";
    public final static String PROVIDER = "com.example.s236307.birthdayify.MainActivity";
    static final int DB_VERSION = 1;
    private final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;
    Cursor mCursor;
    public ContactCP(Context context) {
        this.context = context;
    }

    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER + "/" + TABLE);

    public Uri insert(String firstName, String lastName, String phoneNumber, int date) {
        Uri mNewUri;
        ContentValues mNewValues = new ContentValues(4);
        mNewValues.put(FIRSTNAME, firstName);
        mNewValues.put(LASTNAME, lastName);
        mNewValues.put(PHONENUMBER, phoneNumber);
        mNewValues.put(BIRTHDAY, date);
        mNewUri = context.getContentResolver().insert(CONTENT_URI, mNewValues);
        return mNewUri;
    }

    public boolean delete(String phoneNumber) {
        return db.delete(TABLE, PHONENUMBER + "='" + phoneNumber + "'", null) > 0;
    }

    public boolean update(String firstName, String lastName, int phoneNumber, int birthday) {
        String mSelectionClause = PHONENUMBER + "LIKE ?";
        String[] mselectionArgs = {""};
        mselectionArgs[0] = phoneNumber + "";

        ContentValues cv = new ContentValues(4);
        cv.put(FIRSTNAME, firstName);
        cv.put(LASTNAME, lastName);
        cv.put(PHONENUMBER, phoneNumber);
        cv.put(BIRTHDAY, birthday);
        int mRowUpdated = getContext().getContentResolver().update(
                CONTENT_URI, cv, mSelectionClause, mselectionArgs);
        if(mRowUpdated > 0)
        }
    }

    public Cursor findAll() {
        String[] mProjection = {FIRSTNAME, LASTNAME, PHONENUMBER, BIRTHDAY};
        String[] mSelectionArgs = {""};
        mCursor =  context.getContentResolver().query(CONTENT_URI,
                mProjection, null,
                mSelectionArgs, LASTNAME);
        if(mCursor == null) {
            Log.d(TAG, "The query returned Null!");
        }
        return mCursor;
    }

    public Cursor findOne() {
        return null;
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

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

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "create table " + TABLE + " (" + _ID + " integer primary key autoincrement, "
                    + FIRSTNAME + " text, " + LASTNAME + " text, "
                    + PHONENUMBER + " text, " + BIRTHDAY + " integer);";
            Log.d(TAG, "oncreated sql" + sql);
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + TABLE);
            Log.d(TAG, "updated");
            onCreate(db);
        }
    } // End DatabaseHelper
}
