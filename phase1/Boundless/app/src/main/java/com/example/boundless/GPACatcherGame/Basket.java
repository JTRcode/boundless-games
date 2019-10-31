package com.example.boundless.GPACatcherGame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.boundless.DrawUtility;
import com.example.boundless.GPACatcherGame.falling_objects.FallingObject;
import com.example.boundless.Panel;
import com.example.boundless.R;
import com.example.boundless.GPACatcherGame.falling_objects.FallingObject;

/**
 * A basket for catching falling objects
 */
class Basket {
    /**
     * The appearance of the basket
     */
    private Bitmap appearance;
    private int coordX;
    private int coordY;
    private int size = 250;
    private int speed;
    private int screenWidth;
    private int screenHeight;

    public Basket(int speed) {

        appearance = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.basket);
        appearance = Bitmap.createScaledBitmap(appearance, size, size, true);

        this.screenWidth = Panel.SCREEN_WIDTH;
        this.screenHeight = Panel.SCREEN_HEIGHT;
        setCoordX(screenWidth / 2);
        setCoordY(screenHeight - 250);
        this.speed = speed;
    }

    /**
     * Get the x coordinate of the basket
     *
     * @return The x coordinate of the basket
     */
    int getCoordX() {
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

    /**
     * Moves the basket left on the screen
     */
    void moveLeft() {
        if (coordX >= 0)
            coordX -= speed;
    }

    /**
     * Moves the basket right on the screen
     */
    void moveRight() {
        if (coordX + size < screenWidth)
            coordX += speed;
    }

    /**
     * Checks if an item is in range to be caught by the basket
     *
     * @param object The object that being examined if it is caught by the basket
     * @return return true if it's in the range of basket
     */
    boolean inRange(FallingObject object) {
        int middle = object.getCoordX() + object.getSize() / 2;
        int bottom = object.getCoordY() + object.getSize();

        return (middle >= coordX && middle <= coordX + size && bottom >= coordY);
    }

    /**
     * Draws the Basket
     */
    public void draw() {
        DrawUtility.drawBitmap(appearance, coordX, coordY);
    }
}
