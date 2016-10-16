package com.example.s236307.birthdayify;


import java.util.Date;

public class Contact {
    private String firstName, lastName;
    private int phoneNumber;
    private Date birthday;

    public Contact (String firstName, String lastName, int phoneNumber, Date birthday) {
        this.birthday = birthday;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}
