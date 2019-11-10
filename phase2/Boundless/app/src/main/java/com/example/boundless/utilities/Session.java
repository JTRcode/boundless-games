package com.example.boundless.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.boundless.R;

/**
 * Saves the preferences of the user between opening and closing the app
 */
public class Session {

    private static SharedPreferences prefs;

    private Session() {
    }

    /**
     * Sets up the session, must be called previous to any of the other methods.
     *
     * @param context The current context.
     */
    public static void setupSession(Context context) {
        checkSession(context);
    }

    private static void checkSession(Context context){
        if (prefs == null)
            prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Set the current user
     *
     * @param username The username of the user
     * @param password The password of the user
     */
    public static void setUser(String username, String password) {
        prefs.edit().putString("username", username).apply();
        prefs.edit().putString("password", password).apply();
    }

    /**
     * Clear the current user
     */
    public static void clearUser() {
        setUser(null, null);
    }

    /**
     * Get the current user
     *
     * @return the username and password of the current user
     */
    public static String[] getUser() {
        String username = prefs.getString("username", "");
        String password = prefs.getString("password", "");
        if (username.equals("") || password.equals("")) {
            return new String[]{};
        }
        return new String[]{username, password};
    }

    /**
     * set the users' music
     *
     * @param id the id of the music
     */
    public static void setMusic(int id) {
        prefs.edit().putInt("music", id).apply();
    }

    /**
     * get the users' music
     *
     * @return the users' choice of the music
     * @param context The current context
     */
    public static int getMusic(Context context) {
        checkSession(context);
        return prefs.getInt("music", R.raw.minnutesican);
    }

    /**
     * set the background
     *
     * @param id the id of the background
     */
    public static void setBackground(int id) {
        prefs.edit().putInt("background", id).apply();
    }

    /**
     * return users' choice of background
     *
     * @return the users' choice of background, default is backgroundone
     * @param context The current context
     */
    public static int getBackground(Context context) {
        checkSession(context);
        return prefs.getInt("background", R.drawable.backgroundone);
    }

    /**
     * set the theme on
     *
     * @param on determine if the theme is on or not
     */
    public static void setTheme(boolean on) {
        prefs.edit().putBoolean("theme_on", on).apply();
    }

    /**
     * return the state of the theme
     *
     * @return the theme is on
     * @param context The current context
     */
    public static boolean getTheme(Context context) {
        checkSession(context);
        return prefs.getBoolean("theme_on", false);
    }

}