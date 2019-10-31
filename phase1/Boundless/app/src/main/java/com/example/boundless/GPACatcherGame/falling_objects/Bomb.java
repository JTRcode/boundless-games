package com.example.boundless.GPACatcherGame.falling_objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.boundless.GPACatcherGame.GPACatcherGame;
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
        GPACatcherGame.addLife(-1);
    }

    @Override
    public void missed() {
        GPACatcherGame.bomb_missed();
    }

}
