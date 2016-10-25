package com.example.s236307.birthdayify;


import android.content.ContentValues;

import java.util.Date;

public class Contact {
    private String firstName, lastName, phoneNumber;
    private int birthdayInMillis;
    private Date birthday;

    public int getBirthdayInMillis() {
        return birthdayInMillis;
    }

    public void setBirthdayInMillis(int birthdayInMillis) {
        this.birthdayInMillis = birthdayInMillis;
    }

    public Contact(String firstName, String lastName, String phoneNumber, int birthdayInMillis) {
        this.birthdayInMillis = birthdayInMillis;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        birthday = new Date(birthdayInMillis);
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
