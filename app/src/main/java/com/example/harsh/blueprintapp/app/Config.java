package com.example.harsh.blueprintapp.app;


public final class Config {

    public static final String SECRET_KEY = "";
    public static final String DEVICE_TYPE = "mobile";
    public static final String PLATFORM = "android";
    public static final String JSON_TRUE = "y";
    public static final String JSON_FALSE = "n";
    public static final String DEVICE_OS = "android";


    public interface Priority {

        String HIGH = "High";
        String LOW = "Low";
        String NORMAL = "Normal";
    }


    public interface DateFormat {

        //Need to clean up later
        String API = "dd-MM-yyyy HH:mm:ss Z";
        //Temp for now
        String SHORT_DATE = "MMM dd, yyyy";
        String NORMAL_DATE_24 = "MMM dd, yyyy HH:mm";
        String NORMAL_DATE = "MMM dd, yyyy hh:mm a";
        String CAMPAIGN = SHORT_DATE;
        String DOCUMENT = SHORT_DATE;
        String ACTIVITY_FILTER_DATE = "dd-MM-yyyy";
        String DATE_FOR_SERVER = "dd/MM/yyyy";
        String NOTIFICATION = NORMAL_DATE;
        String DISPLAY_DATE = "EEEE'\n'd MMMM yyyy";
        String DISPLAY_DATE_WITH_TIME = "EEEE'\n'd MMMM yyyy'\nat 'h:mm a";
        String DISPLAY_REMINDER_TIME = "EEEE d MMMM yyyy 'at' h:mm a";
        String TIME_AM_PM = "h:mm a";
        String DATABASE = "yyyy-MM-dd HH:mm:ss";
        String CALL = "EEE MMM dd hh:mm:ss z yyyy";
    }


    public interface Length {

        int TEXT = 80;
        int PHONE_NUMBER = 16;
        int NUMBER = 9;
        int CURRENCY = 16;
        int TEXT_AREA = 256;
        int PASSWORD = 6;
    }

    public interface ApiStatusCodes {

        //names base on https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
        int STATUS_OK = 200;
        int STATUS_GONE = 410;
        int SESSION_INVALID = 401;
        int ALREADY_LOGGED = 409;
    }

    public interface SortOrder {

        String ASC = "asc";
        String DESC = "desc";
    }


}
