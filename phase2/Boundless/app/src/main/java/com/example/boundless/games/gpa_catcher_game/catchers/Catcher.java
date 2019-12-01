package com.example.boundless.games.gpa_catcher_game.catchers;

import android.graphics.Bitmap;


public abstract class Catcher {

    private Bitmap appearance;
    private int coordX;
    private int coordY;
    private int speed;


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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public void setAppearance(Bitmap appearance) {
        this.appearance = appearance;
    }

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


    //TODO should not be caring about screen width

    /**
     * Moves left on the screen
     */
    public abstract void moveLeft();

    /**
     * Moves right on the screen
     */
    public abstract void moveRight();
}
