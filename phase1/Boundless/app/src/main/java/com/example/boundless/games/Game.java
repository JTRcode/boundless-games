package com.example.boundless.games;

import com.example.boundless.Statistics;

import java.util.Observable;

/**
 * A single game
 */
public abstract class Game extends Observable {

    /**
     * Used publicly, checks if the game is over and notifies observers
     *
     * @return if the game is over
     */
    public boolean checkGameOver() {
        if (this.gameOver()) {
            Statistics.sumTotalScore();
            this.setChanged();
            notifyObservers(this);
            return true;
        }
        return false;
    }

    /**
     * Individual implementations of game over in each game
     *
     * @return If the game is over
     */
    abstract boolean gameOver();

    /**
     * Draws the game
     */
    public abstract void draw();

    /**
     * Handles the screen being touched
     *
     * @param x The x coordinate of the touched location
     * @param y The y coordinate of the touched location
     */
    public abstract void screenTouched(int x, int y);

    /**
     * Updates the screen, if necessary
     */
    public void update() {
    }
}
