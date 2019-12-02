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

    /**
     * A new falling assignment
     */
    Assignment() {
        appearance = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.assignment);
        appearance = Bitmap.createScaledBitmap(appearance, getSize(), getSize(), true);
    }

    /**
     * When an Assignment is caught the GPA increases
     * @param status
     */
    @Override
    public void caught(GPAGameStatus status) {
        double caughtValue = 0.1;
        if (status.getDoubleGPA())
            caughtValue *= 2;

        status.addGpa(caughtValue);
    }

    /**
     * When a Assignment is missed we decrease the life and GPA
     * @param status
     */
    @Override
    public void missed(GPAGameStatus status) {
        status.addGpa(-0.1);
        status.addLives(-1);
    }

}
