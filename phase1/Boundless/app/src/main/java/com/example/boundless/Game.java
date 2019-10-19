package com.example.boundless;

import android.graphics.Canvas;

/**
 * A single game
 */
public abstract class Game {
    /**
     * The statistics held for this game
     */
    Statistics stats;

    public abstract boolean gameOver();
    public abstract void draw(Canvas canvas);
    public void update(){
        //TODO
    }
    //TODO
}
