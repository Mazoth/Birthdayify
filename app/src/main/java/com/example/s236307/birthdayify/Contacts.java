package com.example.s236307.birthdayify;

import android.app.Fragment;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by mkluf on 10/21/2016.
 */

public class Contacts extends ListFragment implements LoaderCallbacks<Cursor> {
    LoaderManager loaderManager;
    CursorLoader cursorLoader;
    SimpleCursorAdapter mAdapter;
    String TAG = "Loader";

    public Contacts() {
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(mAdapter != null && data != null) mAdapter.swapCursor(data);
        else Log.v(TAG, "OnLoadFinished: mAdapter is null");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loaderManager = getActivity().getLoaderManager();
        String[] uiBindFrom = {ContactsContract.Contacts.DISPLAY_NAME};
        int[] uiBindTo = {android.R.id.text1};
        mAdapter = new SimpleCursorAdapter(getActivity().getBaseContext(),
                android.R.layout.simple_list_item_1, null, uiBindFrom, uiBindTo, 0);
        ListView l = (ListView) getActivity().findViewById(R.id.contactListview);
        l.setAdapter(mAdapter);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getBaseContext(), position + " klikket",
                        Toast.LENGTH_SHORT).show();
            }
        });
        loaderManager.initLoader(0, null, this);
    }
    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        String[] projection = {ContactsContract.Contacts.DISPLAY_NAME};
        cursorLoader = new CursorLoader(getActivity().getBaseContext(),
                ContactsContract.Contacts.CONTENT_URI, projection, null, null, null);
        return cursorLoader;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contact_list_fragment, container, false);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if(mAdapter != null) mAdapter.swapCursor(null);
        else Log.v(TAG, "OnLoadFinished: mAdapter is null");
    }
}
