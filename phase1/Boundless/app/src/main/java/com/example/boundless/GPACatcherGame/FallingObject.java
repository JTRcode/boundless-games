package com.example.boundless.GPACatcherGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.example.boundless.Panel;

/**
 * Objects that will fall on the screen.
 */
public abstract class FallingObject {
    /**
     * The coordinates of the object
     */
    private int coordX;
    private int coordY;
    /**
     * The appearance of the object
     */
    Bitmap appearance;
    /**
     * The size of the object
     */
    private int size = 100;
    /**
     * The speed the object falls at
     */
    private int fallingSpeed = 15;
    private Paint paintText = new Paint();

    public FallingObject() {
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        setCoordY(0);
        int x = (int) (Math.random() * Panel.SCREEN_WIDTH - size);
        if (x <= size / 2) {
            x = size;
        }
        setCoordX(x);
    }

    /**
     * Sets the size of the object
     *
     * @param size The size to set the object to
     */
    void setSize(int size) {
        this.size = size;
    }

    /**
     * Gets the size of the object.
     *
     * @return The size of the object.
     */
    int getSize() {
        return size;
    }

    /**
     * Get the x coordinate of the object
     *
     * @return The x coordinate of the object
     */
    int getCoordX() {
        return coordX;
    }

    /**
     * Sets the x coordinate of the object
     *
     * @param coordX The new x coordinate to set the object to
     */
    void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    /**
     * Get the y coordinate of the object
     *
     * @return The y coordinate of the object
     */
    int getCoordY() {
        return coordY;
    }

    /**
     * Set the y coordinate of the object
     *
     * @param coordY The new y coordinate to set the object to
     */
    void setCoordY(int coordY) {
        this.coordY = coordY;
    }


    /**
     * Makes the object fall down the screen when called.
     */
    void fall() {
        coordY += fallingSpeed;
    }

    /**
     * Tell if the object has hit the ground
     *
     * @return If the object has hit the bottom of the screen
     */
    boolean hitGround() {
        return (coordY >= Panel.SCREEN_HEIGHT);
    }

    /**
     * @param canvas the canvas on which to draw this item.
     */
    protected void draw(Canvas canvas) {
        canvas.drawBitmap(appearance, coordX, coordY, paintText);
    }

    abstract void caught();

    abstract void missed();
}
