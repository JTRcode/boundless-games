package com.example.boundless.GPACatcherGame;

public class Sleep extends FallingObject {
    /**
     * appr
     */


    /**
     *  + 1 life when caught, no effect when missed.
     */
    @Override
    void caught() {
        GPACatcherGame.addLife(1);
    }

    @Override
    void missed() {

    }

}
