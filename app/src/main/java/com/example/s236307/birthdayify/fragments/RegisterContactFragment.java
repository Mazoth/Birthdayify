package com.example.s236307.birthdayify.fragments;

import android.app.Fragment;
import android.content.ContentValues;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.s236307.birthdayify.Contact;
import com.example.s236307.birthdayify.ContactCP;
import com.example.s236307.birthdayify.HandyTools;
import com.example.s236307.birthdayify.R;

import java.util.Calendar;
import java.util.Date;

public class RegisterContactFragment extends Fragment implements View.OnClickListener {
    private EditText mName, mPhoneNumber;
    private DatePicker mDatePicker;
    private ContactCP contactCP;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mName = (EditText) getView().findViewById(R.id.nameField);
        mPhoneNumber = (EditText) getView().findViewById(R.id.phoneNumberField);
        mDatePicker = (DatePicker) getView().findViewById(R.id.dateOfBirthPicker);
        HandyTools.datePickerInitializing(mDatePicker);
        contactCP = new ContactCP();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.contact_register_fragment, container, false);
        Button registerButton = (Button) v.findViewById(R.id.registerContactButton);
        registerButton.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        String name = mName.getText().toString();
        String phoneNumer = mPhoneNumber.getText().toString();
        long date = HandyTools.getDateInMillis(mDatePicker);
        boolean anyFieldEmpty = false;
        if(TextUtils.isEmpty(name)){
            mName.setError(getString(R.string.emptyEditTextFieldError));
            anyFieldEmpty = true;
        }
        if(TextUtils.isEmpty(phoneNumer)){
            mPhoneNumber.setError(getString(R.string.emptyEditTextFieldError));
            anyFieldEmpty = true;
        } else if(phoneNumer.length() != 8) {
            mPhoneNumber.setError(getString(R.string.phoneLenghtErrorMessage));
            anyFieldEmpty = true;
        }
        if(anyFieldEmpty) return; //Stop here and the user has to input correct.
        ContentValues cv = new ContentValues();
        cv.put(ContactCP.NAME, name);
        cv.put(ContactCP.PHONENUMBER, phoneNumer);
        cv.put(ContactCP.BIRTHDAY, date);
        Toast.makeText(getActivity().getBaseContext(), getActivity().getContentResolver().insert(ContactCP.CONTENT_URI, cv).toString(), Toast.LENGTH_SHORT).show();
    }
}
