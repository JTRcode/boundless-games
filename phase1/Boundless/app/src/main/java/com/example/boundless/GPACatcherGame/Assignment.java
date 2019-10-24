package com.example.boundless.GPACatcherGame;

import android.graphics.Color;

class Assignment extends FallingObject {

    Assignment() {
        super();
        setAppearance("[A]");
        getPaintText().setColor(Color.BLUE);
    }

    /**
     * An assignment; +0.1 GPA when caught, -0.3GPA and -1 life when missed.
     */
    @Override
    void caught() {
        GPACatcherGame.addGpa(0.1);
    }

    @Override
    void missed() {
        GPACatcherGame.addGpa(-0.3);
        GPACatcherGame.addLife(-1);
    }
    /**
     *  An assignment; +0.1 GPA when caught, -0.3GPA and -1 life when missed.
     */
    //TODO: what's the comment above for?
}
