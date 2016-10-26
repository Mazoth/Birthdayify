package com.example.s236307.birthdayify;

import android.app.ListFragment;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;


public class Contacts extends ListFragment {
    SimpleCursorAdapter mAdapter;
    Uri allContacts;
    CursorLoader cursorLoader;
    Cursor cursor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allContacts = ContactCP.CONTENT_URI;
        cursorLoader = new CursorLoader(getActivity().getBaseContext(),
                allContacts, null, null, null, null);
        cursor = cursorLoader.loadInBackground();

    }
}
