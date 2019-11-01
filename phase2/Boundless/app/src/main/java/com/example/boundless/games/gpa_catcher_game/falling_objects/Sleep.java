package com.example.boundless.games.gpa_catcher_game.falling_objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.boundless.games.GPACatcherGame;
import com.example.boundless.Panel;
import com.example.boundless.R;

/**
 * A falling object that adds to lives when caught
 */
class Sleep extends FallingObject {

    Sleep() {
        super();
        appearance = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.sleep);
        appearance = Bitmap.createScaledBitmap(appearance, getSize(), getSize(), true);
    }

    @Override
    public void caught() {
        GPACatcherGame.addLife(1);
    }

    @Override
    public void missed() {
        GPACatcherGame.addLife(0);
    }

}
