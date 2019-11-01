package com.example.boundless.games.gpa_catcher_game.falling_objects;

import android.graphics.BitmapFactory;
import android.graphics.Bitmap;

import com.example.boundless.games.GPACatcherGame;
import com.example.boundless.Panel;
import com.example.boundless.R;

/**
 * A falling assignment, adds to GPA when caught
 */
class Assignment extends FallingObject {

    Assignment() {
        super();
        appearance = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.assignment);
        appearance = Bitmap.createScaledBitmap(appearance, getSize(), getSize(), true);
    }

    @Override
    public void caught() {
        GPACatcherGame.addGpa(0.1);
    }

    @Override
    public void missed() {
        GPACatcherGame.addGpa(-0.1);
        GPACatcherGame.addLife(-1);
    }

}
