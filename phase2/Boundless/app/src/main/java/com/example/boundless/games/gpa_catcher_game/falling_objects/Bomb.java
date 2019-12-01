package com.example.boundless.games.gpa_catcher_game.falling_objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.boundless.Panel;
import com.example.boundless.R;
import com.example.boundless.games.gpa_catcher_game.GPAGameStatus;

/**
 * A falling bomb, removes a life when caught
 */
class Bomb extends FallingObject {
    private static int bombsAvoided;

    /**
     * A new falling bomb
     */
    Bomb() {
        appearance = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.bomb);
        appearance = Bitmap.createScaledBitmap(appearance, getSize(), getSize(), true);
    }

    @Override
    public void caught(GPAGameStatus status) {
        if (status.getBombProtection()) return;

        status.addLives(-1);
    }

    @Override
    public void missed(GPAGameStatus status) {
        bombsAvoided++;
        if (bombsAvoided >= 10) {
            bombsAvoided -= 10;
            status.addLives(1);
        }
    }

}
