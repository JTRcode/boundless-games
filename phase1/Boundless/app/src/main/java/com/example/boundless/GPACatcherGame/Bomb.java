package com.example.boundless.GPACatcherGame;

public class Bomb extends FallingObject {
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
