package com.example.s236307.birthdayify;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

}
public class DBAdapter {
    Context context;
    static final String PHONENUMBER = "phoneNumber";
    static final String TAG = "DbHelper";
    static final String DB_NAME = "birthdayify.db";
    static final String TABLE = "people";
    static final String ID = BaseColumns._ID;
    static final String FIRSTNAME = "firstName";
    static final String LASTNAME = "lastName";
    static final String BIRTHDAY = "birthday";
    static final int DB_VERSION = 1;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context context) {
        this.context = context;
        DBHelper = new DatabaseHelper(context);
    }

    public DBAdapter open () throws SQLException {

    }

    public void close() {
    }

    public void insert(ContentValues cv) {
        db.insert(TABLE, null, cv);
    }
    //TODO: Make sure this is right, maybe id = integer?
    public boolean delete(String phoneNumber) {
        return db.delete(TABLE, PHONENUMBER + "='" + phoneNumber + "'", null) > 0;
    }

    public boolean update(String firstName, String lastName, int phoneNumber, int birthday) {
        ContentValues cv = new ContentValues(4);
        cv.put(FIRSTNAME, firstName);
        cv.put(LASTNAME, lastName);
        cv.put(PHONENUMBER, phoneNumber);
        cv.put(BIRTHDAY, birthday);
        return db.update(TABLE, cv, PHONENUMBER + "='" + phoneNumber + "'", null) > 0;
    }

    public Cursor findAll() {
        String[] cols = {FIRSTNAME, LASTNAME, PHONENUMBER, BIRTHDAY};
        return db.query(TABLE, cols, null, null, null, null, LASTNAME);
    }

    public Cursor findOne() {

    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "create table " + TABLE + " (" + ID + " integer primary key autoincrement, "
                    + FIRSTNAME + " text, " + LASTNAME + " text, " + BIRTHDAY + " integer);";
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
