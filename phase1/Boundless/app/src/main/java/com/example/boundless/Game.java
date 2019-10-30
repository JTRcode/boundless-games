package com.example.boundless;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.Toast;

import java.util.Observable;

/**
 * A single game
 */
public abstract class Game extends Observable {
    private Context context;

    public Game(Context context) {
        this.context = context;
    }

    /**
     * Used publicly, checks if the game is over and notifies observers
     *
     * @return if the game is over
     */
    boolean checkGameOver() {
        if (this.gameOver()) {
            Statistics.sumTotalScore();
            this.setChanged();
            notifyObservers(this);
            return true;
        }
        return false;
    }

    /**
     * Used by checkGameOver() only, individual implementations of game over methods
     *
     * @return If the game is over
     */
    public abstract boolean gameOver();

    public abstract void draw(Canvas canvas);

    public abstract void screenTouched(int x, int y);

    public void update() {
    }

    //TODO: update all the javadocs
}
