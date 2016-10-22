package com.example.s236307.birthdayify;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

public class ContactCP extends ContentProvider {
    static final String TAG = "DbHelper";
    static final String PROVIDER = "com.example.provider.BirthdayPeople";
    static final String _ID = "_id";
    static final String NAME = "name";
    static final String PHONENUMBER = "phoneNumber";
    static final String BIRTHDAY = "birthday";

    static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER + "/contacts");

    static final int CONTACTS = 1;

    static final int CONTACTS_ID = 2;
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER, "contacts", CONTACTS);
        uriMatcher.addURI(PROVIDER, "contacts/#", CONTACTS_ID);
    }

    //DB usage
    static final int DB_VERSION = 1;
    static final String DB_NAME = "birthdayify.db";
    static final String TABLE = "contacts";
    SQLiteDatabase db;
    static final String DATABASE_CREATE =
            "create table" + TABLE + " (" + _ID + " integer primary key autoincrement, "
                    + NAME + " text not null, " + PHONENUMBER + " text not null, " + BIRTHDAY + "integer);";

    @Override
    public boolean onCreate() {
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        db = dbHelper.getWritableDatabase();
        return (db != null);
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
        sqlBuilder.setTables(TABLE);
        if (uriMatcher.match(uri) == CONTACTS_ID)
            sqlBuilder.appendWhere(_ID + " = " + uri.getPathSegments().get(1));
        if (sortOrder == null || sortOrder == "")
            sortOrder = NAME;
        Cursor cursor = sqlBuilder.query(
                db,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        switch (uriMatcher.match(uri)) {
            case CONTACTS:
                return "vnd.android.cursor.dir/vnd.example.contacts";
            case CONTACTS_ID:
                return "vnd.android.cursor.item/vnd.example.contacts";
            default:
                throw new IllegalArgumentException("Unsupported URI:  + uri");
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowID = db.insert(TABLE, "", values);
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Failed to insert row into " + uri);
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case CONTACTS:
                count = db.delete(TABLE, selection, selectionArgs);
                break;
            case CONTACTS_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(TABLE, _ID + " = " + id + (!TextUtils.isEmpty(selection) ? " AND ("
                        + selection + ")" : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case CONTACTS:
                count = db.update(TABLE, values, selection, selectionArgs);
                break;
            case CONTACTS_ID:
                String id = uri.getPathSegments().get(1);
                count = db.update(TABLE, values, _ID + " = " + id + (!TextUtils.isEmpty(selection)
                        ? " AND (" + selection + ")" : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(TAG, "oncreated sql" + DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + TABLE);
            Log.d(TAG, "updated");
            onCreate(db);
        }
    } // End DatabaseHelper
}
