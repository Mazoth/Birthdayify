package com.example.s236307.birthdayify;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<Contact> contactList;

    public CustomListAdapter(Activity activity, ArrayList<Contact> contactList) {
        this.activity = activity;
        this.contactList = contactList;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        TextView nameText = (TextView) convertView.findViewById(R.id.nameText);
        TextView phoneNumberText = (TextView) convertView.findViewById(R.id.phoneNumberText);
        TextView birthdayText = (TextView) convertView.findViewById(R.id.birthdayText);

        Contact contact = contactList.get(position);
        nameText.setText(contact.getFirstName() + " " + contact.getLastName());
        phoneNumberText.setText(contact.getPhoneNumber());
        birthdayText.setText();
    }
}
