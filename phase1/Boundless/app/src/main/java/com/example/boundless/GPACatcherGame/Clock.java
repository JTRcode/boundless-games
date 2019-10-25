package com.example.boundless.GPACatcherGame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.example.boundless.Panel;
import com.example.boundless.R;

class Clock extends FallingObject {

    Clock() {
        super();
//        setAppearance("[C]");
//        getPaintText().setColor(Color.GREEN);
        appearance = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.time);
        appearance = Bitmap.createScaledBitmap(appearance, getSize(), getSize(), true);
    }

    /**
     * + some time when caught, no effect when missed
     * current design: add 5 more seconds when caught
     */
    @Override
    void caught() {
        GPACatcherGame.addTime(5);
    }

    @Override
    void missed() {
        GPACatcherGame.addTime(-1);
    }

}
