package com.example.boundless.games.gpa_catcher_game.falling_objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.boundless.Panel;
import com.example.boundless.R;
import com.example.boundless.games.game_utilities.GameResources;
import com.example.boundless.games.gpa_catcher_game.GPAGameStatus;

/**
 * A falling clock, adds time
 */
class Clock extends FallingObject {

    /**
     * A new falling clock
     */
    Clock() {
        appearance = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.time);
        appearance = Bitmap.createScaledBitmap(appearance, getSize(), getSize(), true);
    }

    /**
     * when Caught, it will increase the time the player has
     * @param status
     */
    @Override
    public void caught(GPAGameStatus status) {
        status.addTime(GameResources.GPAGAME_INCREASE_TIME_BY);
    }

    /**
     * When missed nothing will occur
     * @param status
     */
    @Override
    public void missed(GPAGameStatus status) {
        //Nothing happens when a clock hits the ground
    }

}
