package com.example.boundless.utilities;

import android.content.Context;
import android.media.MediaPlayer;
import android.text.Layout;
import android.view.View;
import android.view.Window;

import com.example.boundless.CustomizationActivity;
import com.example.boundless.LevelActivity;
import com.example.boundless.LoginActivity;
import com.example.boundless.MenuActivity;
import com.example.boundless.R;
import com.example.boundless.StatisticsActivity;

import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Handles the customizations for each page
 */
public class HandleCustomization {

    private static MediaPlayer player;

    /**
     * Sets the background for an activity
     * @param context The context of the activity
     * @param window The window of the activity
     */
    public static void setActivityBackground(Context context, Window window){
        int background = getBackgroundDrawable(context);

        window.setBackgroundDrawableResource(background);
    }

    private static int getBackgroundDrawable(Context context) {
        boolean themeOn = Session.getTheme(context);
        if (context instanceof MenuActivity)
            return themeOn ? R.drawable.hallo_menu : R.drawable.menu;
        else if (context instanceof LoginActivity)
            return themeOn ? R.drawable.halloween_log_in : R.drawable.log_in;
        else if (context instanceof CustomizationActivity)
            return themeOn ? R.drawable.halloween_custom : R.drawable.custom;
        else if (context instanceof StatisticsActivity)
            return R.drawable.stats;
        else if (context instanceof LevelActivity)
            return R.drawable.level;
        return Session.getBackground(context);
    }

    /**
     * Sets the background for a game
     * @param context The context for the game
     * @param layout The ConstraintLayout for the background of the game
     */
    public static void setGameBackground(Context context, View layout){
        layout.setBackgroundResource(Session.getBackground(context));
    }

    public static void startMusic(Context context){
        player = MediaPlayer.create(context, Session.getMusic(context));
        player.setLooping(true);
        player.start();
    }

    public static void pauseMusic(Context context){
        if (player != null) {
            player.release();
            player = null;
        }
    }
}
