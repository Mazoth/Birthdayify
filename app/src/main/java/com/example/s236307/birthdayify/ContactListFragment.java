package com.example.s236307.birthdayify;

import android.app.ListFragment;
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


public class ContactListFragment extends ListFragment implements OnItemClickListener {
    private DBAdapter dbAdapter;
    private Cursor cursor;
    private SimpleCursorAdapter cursorAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dbAdapter = new DBAdapter(getActivity());
        cursor = dbAdapter.findAll();
        View view = inflater.inflate(R.layout.contact_list_fragment, container, false);
        cursorAdapter = new SimpleCursorAdapter(
                getActivity(), android.R.layout.simple_list_item_1, cursor,
                new String [] {"database_table_1", "database_table_2"}, null, 0);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Planets,
                R.layout.list_row);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }
}
