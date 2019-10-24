package com.example.boundless;

import android.graphics.Canvas;

/**
 * A single game
 */
public abstract class Game {

    public abstract boolean gameOver();
    public abstract void draw(Canvas canvas);
    public abstract void screenTouched(int x, int y);
    public void update(){}

    //TODO: update all the javadocs
}
