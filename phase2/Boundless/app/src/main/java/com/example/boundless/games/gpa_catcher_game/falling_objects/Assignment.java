package com.example.boundless.games.gpa_catcher_game.falling_objects;

import android.graphics.BitmapFactory;
import android.graphics.Bitmap;

import com.example.boundless.Panel;
import com.example.boundless.R;
import com.example.boundless.games.gpa_catcher_game.GPAGameStatus;

/**
 * A falling assignment, adds to GPA when caught
 */
class Assignment extends FallingObject {

    Assignment() {
        super();
        appearance = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.assignment);
        appearance = Bitmap.createScaledBitmap(appearance, getSize(), getSize(), true);
    }

    public void caught(GPAGameStatus level) {
        double caughtValue = 0.1;
        if (level.getDoubleGPA()){
            caughtValue *= 2;
        }
        level.setGpa(level.getGpa() + caughtValue);
    }

    public void missed(GPAGameStatus level) {
        level.setGpa(level.getGpa()-0.1);
        level.setLives(level.getLives()-1);
    }

}
