package com.example.boundless.games.gpa_catcher_game.falling_objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.boundless.games.GPACatcherGameFacade;
import com.example.boundless.Panel;
import com.example.boundless.R;

/**
 * A falling bomb, removes a life when caught
 */
class Bomb extends FallingObject {

    Bomb() {
        super();
        appearance = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.bomb);
        appearance = Bitmap.createScaledBitmap(appearance, getSize(), getSize(), true);
    }

    @Override
    public void caught() {
        GPACatcherGameFacade.addLife(-1);
    }

    @Override
    public void missed() {
        GPACatcherGameFacade.bombMissed();
    }

}
