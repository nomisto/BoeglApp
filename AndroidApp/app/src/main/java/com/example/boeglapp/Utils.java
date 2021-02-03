package com.example.boeglapp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {
    public static long to_timestamp(String dateStr) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
        java.util.Date date = dateFormat.parse(dateStr);
        assert date != null;
        return date.getTime() / 1000;
    }

    public static String from_timestamp(long timestamp){
        java.util.Date date=new java.util.Date((long)timestamp*1000);
        DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
        return dateFormat.format(date);
    }


}
