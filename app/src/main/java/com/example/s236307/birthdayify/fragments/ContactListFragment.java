package com.example.s236307.birthdayify.fragments;

import android.app.ListFragment;
import android.content.CursorLoader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.s236307.birthdayify.R;


public class ContactListFragment extends ListFragment implements OnItemClickListener {
    private static final String TAG = "ContactListFragment";
    Cursor cursor;
    SimpleCursorAdapter mAdapter;
    CursorLoader cursorLoader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_list_fragment, container, false);
//        cursorLoader = new CursorLoader(getActivity().getBaseContext(),
//                ContactCP.CONTENT_URI, null, null, null, null);
//        cursor = cursorLoader.loadInBackground();
//        String[] columns = new String[]{ContactCP.NAME, ContactCP.PHONENUMBER, ContactCP.BIRTHDAY};
//        int[] views = new int[]{R.id.contactName, R.id.phoneNumber, R.id.birthdayText};
//        mAdapter = new SimpleCursorAdapter(getActivity().getBaseContext(), R.layout.activity_main,
//                cursor, columns, views, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
//        this.setListAdapter(mAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Planets,
                android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }
}
