package com.example.boundless.GPACatcherGame;

public class Clock extends FallingObject {
    /**
     *  + some time when caught, no effect when missed
     *  current design: add 5 more seconds when caught
     */
    @Override
    void caught() {
        GPACatcherGame.addTime(5);
    }

    @Override
    void missed() {

    }

}
