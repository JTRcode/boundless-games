package com.example.boundless.games.gpa_catcher_game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.boundless.games.game_utilities.GameResources;
import com.example.boundless.stats.Statistics;
import com.example.boundless.utilities.DrawUtility;
import com.example.boundless.games.gpa_catcher_game.falling_objects.FallingObject;
import com.example.boundless.Panel;
import com.example.boundless.R;

/**
 * A basket for catching falling objects
 */
public class Basket {
    /**
     * The appearance of the basket
     */
    private Bitmap appearance;
    private int coordX;
    private int coordY;

    public Basket() {

        appearance = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.basket);
        int size = GameResources.GPAGAME_BASKET_SIZE;
        appearance = Bitmap.createScaledBitmap(appearance, size, size, true);

        //this.speed = speed;
    }

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
    void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    /**
     * Get the y coordinate of the basket
     *
     * @return The y coordinate of the basket
     */
    int getCoordY() {
        return coordY;
    }

    /**
     * Set the y coordinate of the basket
     *
     * @param coordY The new y coordinate to set the basket to
     */
    void setCoordY(int coordY) {
        this.coordY = coordY;
    }
//TODO should not be caring about screen width
    /**
     * Moves the basket left on the screen
     */
    public void moveLeft(int steps) {
        coordX -= steps;
    }

    /**
     * Moves the basket right on the screen
     */
    public void moveRight(int steps) {
        coordX += steps;
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
}
