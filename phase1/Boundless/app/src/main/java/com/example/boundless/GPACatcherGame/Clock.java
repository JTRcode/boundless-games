package com.example.boundless.GPACatcherGame;

import android.graphics.Color;

class Clock extends FallingObject {

    Clock() {
        super();
        setAppearance("[C]");
        getPaintText().setColor(Color.GREEN);
    }

    /**
     * + some time when caught, no effect when missed
     * current design: add 5 more seconds when caught
     */
    @Override
    void caught() {
        GPACatcherGame.addTime(5);
    }

    @Override
    void missed() {
        //TODO: Cynthia/Huiqin unsure what this is used for, implement or remove
    }

}
