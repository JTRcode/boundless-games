package com.example.boundless.GPACatcherGame;

import android.graphics.Color;

class Bomb extends FallingObject {

    Bomb(){
        super();
        setAppearance("[B]");
        getPaintText().setColor(Color.RED);
    }
    /**
     *  -1 life when caught, no effect when missed
     */
    @Override
    void caught() {
        GPACatcherGame.addLife(-1);
    }

    @Override
    void missed() {

    }

}
