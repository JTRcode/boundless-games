package com.example.boundless.GPACatcherGame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.example.boundless.Panel;
import com.example.boundless.R;

class Clock extends FallingObject {

    Clock() {
        super();
        appearance = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.time);
        appearance = Bitmap.createScaledBitmap(appearance, getSize(), getSize(), true);
    }

    @Override
    void caught() {
        GPACatcherGame.addTime(5);
    }

    @Override
    void missed() {
        GPACatcherGame.addTime(0);
    }

}
