package com.example.boundless.games.gpa_catcher_game.falling_objects;

import android.graphics.Bitmap;

import com.example.boundless.games.game_utilities.GameResources;
import com.example.boundless.games.gpa_catcher_game.GPAGameStatus;
import com.example.boundless.utilities.DrawUtility;
import com.example.boundless.Panel;

/**
 * Objects that will fall on the screen.
 */
public abstract class FallingObject {
    /**
     * The appearance of the object
     */
    protected Bitmap appearance;

    private int coordX;
    private int coordY = 0;
    private int size = 100;

    /**
     * A new falling object
     */
    FallingObject() {
        int x = (int) (Math.random() * Panel.SCREEN_WIDTH - size);
        coordX = (x <= size / 2) ? size : x;
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
     * Get the bitmap image of this Falling Object
     *
     * @return Bitmap image
     */
    public Bitmap getAppearance() {
        return appearance;
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
     * Get the y coordinate of the object
     *
     * @return The y coordinate of the object
     */
    public int getCoordY() {
        return coordY;
    }

    /**
     * Makes the object fall down the screen when called.
     */
    public void fall() {
        coordY += GameResources.GPAGAME_DEFAULT_FALLING_SPEED;
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
    public abstract void caught(GPAGameStatus status);

    /**
     * What happens when the object is missed
     */
    public abstract void missed(GPAGameStatus status);
}
