package com.example.boundless.GPACatcherGame;

import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.graphics.Color;

import com.example.boundless.Panel;
import com.example.boundless.R;

class Assignment extends FallingObject {

    Assignment() {
        super();
        appearance = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.assignment);
        appearance = Bitmap.createScaledBitmap(appearance, getSize(), getSize(), true);
    }

    @Override
    void caught() {
        GPACatcherGame.addGpa(0.1);
    }

    @Override
    void missed() {
        GPACatcherGame.addGpa(-0.1);
        GPACatcherGame.addLife(-1);
    }

}
