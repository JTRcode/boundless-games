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

    @Override
    public void caught(GPAGameStatus level) {
        double caughtValue = 0.1;
        if (level.getDoubleGPA()){
            caughtValue *= 2;
        }
        double gpa = Math.min(4.0, level.getGpa() + caughtValue);
        level.setGpa(gpa);
    }

    @Override
    public void missed(GPAGameStatus level) {
        level.setGpa(level.getGpa()-0.1);
        level.setLives(level.getLives()-1);
    }

}
