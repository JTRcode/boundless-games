package com.example.boundless.games.gpa_catcher_game.catchers;

import android.graphics.Bitmap;

import com.example.boundless.games.game_utilities.GameResources;

/**
 * A catcher to catch falling objects
 */
public abstract class Catcher {

    private Bitmap appearance;
    /**
     * The x location of the catcher
     */
    protected int coordX;
    private int coordY;
    /**
     * The speed of the basket
     */
    int speed = GameResources.GPAGAME_DEFAULT_BASKET_SPEED;


    /**
     * Get the x coordinate of the basket
     *
     * @return The x coordinate of the basket
     */
    public int getCoordX() {
        return coordX;
    }

    /**
     * Set the x coordinate of the basket
     *
     * @param coordX The new x coordinate to set the basket to
     */
    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    /**
     * Get the y coordinate of the basket
     *
     * @return The y coordinate of the basket
     */
    public int getCoordY() {
        return coordY;
    }

    /**
     * Set the y coordinate of the basket
     *
     * @param coordY The new y coordinate to set the basket to
     */
    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    /**
     * Set the speed of the catcher
     *
     * @param speed The speed to set the catcher to
     */
    protected void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Set the appearance of the catcher
     *
     * @param appearance The catcher's appearance
     */
    protected void setAppearance(Bitmap appearance) {
        this.appearance = appearance;
    }

    /**
     * Get teh appearance of the catcher
     *
     * @return The catcher's appearance
     */
    public Bitmap getAppearance() {
        return appearance;
    }

    /**
     * Gets the size of the object.
     *
     * @return The size of the object.
     */
    public int getSize() {
        return appearance.getWidth();
    }

    /**
     * Moves left on the screen
     */
    public abstract void moveLeft();

    /**
     * Moves right on the screen
     */
    public abstract void moveRight();
}
