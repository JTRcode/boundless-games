package com.example.boundless.GPACatcherGame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.example.boundless.Panel;
import com.example.boundless.R;

class Bomb extends FallingObject {

    Bomb() {
        super();
//        setAppearance("[B]");
//        getPaintText().setColor(Color.RED);
        appearance = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.bomb);
        appearance = Bitmap.createScaledBitmap(appearance, getSize(), getSize(), true);
    }

    /**
     * -1 life when caught, no effect when missed
     */
    @Override
    void caught() {
        GPACatcherGame.addLife(-1);
    }

    @Override
    void missed() {
        GPACatcherGame.bomb_missed();
    }

}
