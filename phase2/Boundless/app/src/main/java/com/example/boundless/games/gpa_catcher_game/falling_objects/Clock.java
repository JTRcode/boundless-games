package com.example.boundless.games.gpa_catcher_game.falling_objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.boundless.games.GPACatcherGame;
import com.example.boundless.Panel;
import com.example.boundless.R;

/**
 * A falling clock, adds time
 */
class Clock extends FallingObject {

    Clock() {
        super();
        appearance = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.time);
        appearance = Bitmap.createScaledBitmap(appearance, getSize(), getSize(), true);
    }

    @Override
    public void caught() {
        GPACatcherGame.addTime(50);
    }

    @Override
    public void missed() {
        GPACatcherGame.addTime(0);
    }

}
