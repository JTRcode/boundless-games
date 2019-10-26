package com.example.boundless;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

class Session {

    /**
     * Saved preferenes
     */
    private static SharedPreferences prefs;

    Session(Context context) {
        if (prefs == null)
            prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Set the current user
     *
     * @param username The username of the user
     * @param password The password of the user
     */
    static void setUser(String username, String password) {
        prefs.edit().putString("username", username).apply();
        prefs.edit().putString("password", password).apply();
    }

    /**
     * Clear the current user
     */
    static void clearUser() {
        setUser(null, null);
    }

    /**
     * Get the current user
     *
     * @return the username and password of the current user
     */
    static String[] getUser() {
        String username = prefs.getString("username", "");
        String password = prefs.getString("password", "");
        if (username.equals("") || password.equals("")) {
            return new String[]{};
        }
        return new String[]{username, password};
    }
}