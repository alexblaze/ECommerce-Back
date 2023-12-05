package com.sanjay.helper;

public interface DateTimeFormats {
    String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    String DATE_TIME_UTC = "yyyy-MM-dd HH:mm:ss z"; // also provide timezone in @JsonFormat when using this format
    String DATE = "yyyy-MM-dd";
    String DATE_MONTH = "yyyy-MMM-dd";
    String TIME_FULL = "HH:mm:ss";
    String TIME_HOURS_MINUTES = "HH:mm";
}
