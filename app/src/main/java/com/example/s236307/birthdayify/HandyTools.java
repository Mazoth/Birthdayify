package com.example.s236307.birthdayify;

import android.text.TextUtils;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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



    public static void datePickerInitializing(DatePicker datePicker) {
        Calendar today = Calendar.getInstance();
        datePicker
                .updateDate(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH));
        datePicker.setMaxDate(today.getTimeInMillis());
    }

    public static long getDateInMillis (DatePicker datePicker) {
        Calendar c = Calendar.getInstance();
        c.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        return c.getTimeInMillis();
    }

    public static String getMonthAndDayInString(long timeInMillis) {
        SimpleDateFormat sf = new SimpleDateFormat("dd MMM", new Locale("no"));
        return sf.format(new Date(timeInMillis));
    }
}
