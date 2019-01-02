package com.example.harsh.blueprintapp.session;


import android.content.Context;
import android.content.SharedPreferences;

/**
 * version V1.
 */

public class SessionManager {
    // Shared Preferences
    private SharedPreferences pref;

    // Editor for Shared preferences
    private SharedPreferences.Editor editor;

    // Context
    private Context context;

    // Shared pref mode
    private int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AppPref";


    private static final String KEY_TOKEN = "token";
    private static final String KEY_FILTER = "filter";

    // Constructor
    public SessionManager(Context context) {
        this.context = context;
        pref = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     */
    public void createLoginSession(String token) {

        editor.putString(KEY_TOKEN, token);

        editor.commit();
    }


    public void setSortOrder(boolean filter) {
        editor.putBoolean(KEY_FILTER, filter);
        editor.commit();
    }


    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();


    }

    /**
     * Quick check for login
     **/
    // Get Login State
    public String getToken() {
        return pref.getString(KEY_TOKEN, null);
    }

    // Get Login State
    public boolean getKeyFilter() {
        return pref.getBoolean(KEY_FILTER, false);
    }
}