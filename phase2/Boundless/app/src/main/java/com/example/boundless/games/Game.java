package com.example.boundless.games;

import android.view.MotionEvent;

import java.util.Observable;

/**
 * A single game
 */
public abstract class Game extends Observable {

    private boolean notificationSent = false;
    private boolean gameIsOver = false;

    /**
     * Used publicly, checks if the game is over and notifies observers
     *
     * @return if the game is over
     */
    public boolean checkGameOver() {
        if (gameOver()) {
            gameIsOver = true;
            if (!notificationSent) {
                notificationSent = true;
                setChanged();
                notifyObservers(getGameOverText());
            }
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
     * @param event The motion event of the screen being touched
     */
    public void screenTouched(MotionEvent event){
        final int pointerCount = event.getPointerCount();
        for (int pointer = 0; pointer < pointerCount; pointer++) {
            MotionEvent.PointerCoords coords = new MotionEvent.PointerCoords();
            event.getPointerCoords(pointer, coords);
            this.screenTouched((int) coords.x, (int) coords.y);
        }
    }

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

    /**
     * Get the instructions for the game
     *
     * @return The game's instructions
     */
    abstract String getInstructions();

    /**
     * Text to show when the game is over
     *
     * @return The game's "game over" text
     */
    abstract String getGameOverText();

    /**
     * Tells if the game has already been played
     *
     * @return if the game is over
     */
    public boolean isGameOver() {
        return gameIsOver;
    }

    /**
     * Shows the game's instructions
     */
    public void showInstructions() {
        setChanged();
        notifyObservers(getInstructions());
    }

    /**
     * Sets a hint for the game
     */
    public void setHint() {
    }
}
