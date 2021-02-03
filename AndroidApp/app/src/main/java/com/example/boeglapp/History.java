package com.example.boeglapp;

import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class History {
    public int Id;
    public int Toolid;
    public int Siteid;
    public String Employee;
    public int To_date;
    public int From_date;

    public String get_formatted_to_string(){
        return Utils.from_timestamp(To_date);
    }

    public String get_formatted_from_string(){
        return Utils.from_timestamp(From_date);
    }
}
