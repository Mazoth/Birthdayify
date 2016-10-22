package com.example.s236307.birthdayify;

import java.util.Calendar;
import java.util.Date;

public class HandyTools {

    //Method that compares a contact's birthday with given @param date
    public static boolean isItTimeForCake(Contact contact, Date date) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(contact.getBirthday());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        return calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) &&
                calendar2.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
    }

    //Method mainly intended to be used for widget and displaying time until next contact's birthday


}
