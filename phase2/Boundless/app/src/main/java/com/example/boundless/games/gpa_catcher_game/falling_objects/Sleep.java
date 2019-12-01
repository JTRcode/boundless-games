package com.example.boundless.games.gpa_catcher_game.falling_objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.boundless.Panel;
import com.example.boundless.R;
import com.example.boundless.games.gpa_catcher_game.GPAGameStatus;

/**
 * A falling object that adds to lives when caught
 */
class Sleep extends FallingObject {

    /**
     * A new sleep falling object
     */
    Sleep() {
        appearance = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.sleep);
        appearance = Bitmap.createScaledBitmap(appearance, getSize(), getSize(), true);
    }

    @Override
    public void caught(GPAGameStatus status) {
        status.addLives(1);
    }

    @Override
    public void missed(GPAGameStatus status) {
        //Nothing happens when sleep is missed, although maybe it should...
    }

}
