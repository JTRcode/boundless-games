package com.example.boundless.GPACatcherGame;

import android.graphics.Color;

class Sleep extends FallingObject {

    Sleep() {
        super();
        setAppearance("[S]");
        getPaintText().setColor(Color.BLACK);
    }


    /**
     * + 1 life when caught, no effect when missed.
     */
    @Override
    void caught() {
        GPACatcherGame.addLife(1);
    }

    @Override
    void missed() {

    }

}
