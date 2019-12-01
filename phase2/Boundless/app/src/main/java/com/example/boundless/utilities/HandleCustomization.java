package com.example.boundless.utilities;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.example.boundless.activities.AchievementsActivity;
import com.example.boundless.activities.CustomizationActivity;
import com.example.boundless.activities.LevelActivity;
import com.example.boundless.activities.LoginActivity;
import com.example.boundless.activities.MenuActivity;
import com.example.boundless.R;
import com.example.boundless.activities.StatisticsActivity;

/**
 * Handles the customizations for each page
 */
public class HandleCustomization {
    private static boolean themeOn = false;
    private static MediaPlayer player;

    private HandleCustomization() {
    }

    /**
     * Sets the background for an activity
     *
     * @param context The context of the activity
     * @param window  The window of the activity
     */
    public static void setActivityBackground(Context context, Window window) {
        int background = getBackgroundDrawable(context);
        try {
            window.setBackgroundDrawableResource(background);
        } catch (Resources.NotFoundException e) {
            Log.e("HandleCustomization", "Window resource not found, defaulting");
            window.setBackgroundDrawableResource(R.drawable.backgroundone);
            Session.setTheme(false);
        }
    }

    private static int getBackgroundDrawable(Context context) {
        themeOn = Session.getTheme(context);
        if (context instanceof MenuActivity)
            return themeOn ? R.drawable.christ_menu : R.drawable.menu;
        else if (context instanceof LoginActivity)
            return themeOn ? R.drawable.christ_login : R.drawable.log_in;
        else if (context instanceof CustomizationActivity)
            return themeOn ? R.drawable.christ_custom : R.drawable.custom;
        else if (context instanceof StatisticsActivity)
            return themeOn ? R.drawable.christ_stats : R.drawable.stats;
        else if (context instanceof LevelActivity)
            return themeOn ? R.drawable.christ_level : R.drawable.level;
        else if (context instanceof AchievementsActivity)
            return themeOn ? R.drawable.christ_ach : R.drawable.ach;
        return Session.getBackground(context);
    }

    /**
     * Sets the background for a game
     *
     * @param context The context for the game
     * @param layout  The ConstraintLayout for the background of the game
     */
    public static void setGameBackground(Context context, View layout) {
        try {
            themeOn = Session.getTheme(context);
            layout.setBackgroundResource(Session.getBackground(context));
        } catch (Resources.NotFoundException e) {
            setDefaultBackground(layout);
        }
    }

    private static void setDefaultBackground(View view) {
        Log.e("HandleCustomization", "Resource not found, defaulting");
        Session.setTheme(false);
        Session.setBackground(R.drawable.backgroundtwo);
        view.setBackgroundResource(R.drawable.backgroundone);

    }

    /**
     * Start the music
     *
     * @param context The current context
     */
    public static void startMusic(Context context) {
        try {
            player = MediaPlayer.create(context, Session.getMusic(context));
        } catch (Resources.NotFoundException e) {
            player = MediaPlayer.create(context, R.raw.minnutesican);
            Session.setTheme(false);
        }
        player.setLooping(true);
        player.start();
    }

    /**
     * Pause the music
     *
     * @param context The current context
     */
    public static void pauseMusic(Context context) {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }

    /**
     * Get the label color of the pixels.
     *
     * @return The color that the pixel's labels should be.
     */
    public static int getPixelLabelColor() {
        return (themeOn) ? Color.RED : Color.WHITE;
    }
}
