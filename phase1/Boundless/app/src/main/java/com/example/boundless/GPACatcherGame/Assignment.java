package com.example.boundless.GPACatcherGame;

import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.graphics.Color;

import com.example.boundless.Panel;
import com.example.boundless.R;

class Assignment extends FallingObject {

    Assignment() {
        super();
//        setAppearance("[A]");
        appearance = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.assignment);
        appearance = Bitmap.createScaledBitmap(appearance, getSize(), getSize(), true);
//        getPaintText().setColor(Color.BLUE);
    }

    @Override
    void caught() {
        GPACatcherGame.addGpa(0.1);
    }

    @Override
    void missed() {
        GPACatcherGame.addGpa(-0.3);
        GPACatcherGame.addLife(-1);
    }

}
