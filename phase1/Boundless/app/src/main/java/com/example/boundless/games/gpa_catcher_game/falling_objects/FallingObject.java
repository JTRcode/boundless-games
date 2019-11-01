package com.example.boundless.games.gpa_catcher_game.falling_objects;

import android.graphics.Bitmap;

import com.example.boundless.utilities.DrawUtility;
import com.example.boundless.Panel;

/**
 * Objects that will fall on the screen.
 */
public abstract class FallingObject {
    /**
     * The appearance of the object
     */
    Bitmap appearance;

    private int coordX;
    private int coordY;
    private int size = 100;
    private int fallingSpeed = 15;

    public FallingObject() {
        setCoordY(0);
        //TODO: move this to another method
        int x = (int) (Math.random() * Panel.SCREEN_WIDTH - size);
        x = (x <= size / 2) ? size : x;
        setCoordX(x);
    }

    /**
     * Sets the size of the object
     *
     * @param size The size to set the object to
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Gets the size of the object.
     *
     * @return The size of the object.
     */
    public int getSize() {
        return size;
    }

    /**
     * Get the x coordinate of the object
     *
     * @return The x coordinate of the object
     */
    public int getCoordX() {
        return coordX;
    }

    /**
     * Sets the x coordinate of the object
     *
     * @param coordX The new x coordinate to set the object to
     */
    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    /**
     * Get the y coordinate of the object
     *
     * @return The y coordinate of the object
     */
    public int getCoordY() {
        return coordY;
    }

    /**
     * Set the y coordinate of the object
     *
     * @param coordY The new y coordinate to set the object to
     */
    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }


    /**
     * Makes the object fall down the screen when called.
     */
    public void fall() {
        coordY += fallingSpeed;
    }

    /**
     * Tell if the object has hit the ground
     *
     * @return If the object has hit the bottom of the screen
     */
    public boolean hitGround() {
        return (coordY >= Panel.SCREEN_HEIGHT);
    }

    /**
     * Draws the object falling
     */
    public void draw() {
        DrawUtility.drawBitmap(appearance, coordX, coordY);
    }

    /**
     * What happens when the object is caught
     */
    public abstract void caught();

    /**
     * What happens when the object is missed
     */
    public abstract void missed();
}
