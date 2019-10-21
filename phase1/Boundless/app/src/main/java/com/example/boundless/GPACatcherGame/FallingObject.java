package com.example.boundless.GPACatcherGame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.example.boundless.Panel;

/**
 * Objects that will fall on the screen.
 */
public abstract class FallingObject {

    private int coordX; //The first coordinate of the object
    private int coordY; //The second coordinate of the object
    private String appearance; //The appearance of the object
    private int fallingSpeed = 2; //The speed that the object will fall at
    private Paint paintText = new Paint();

    public FallingObject(){
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        setCoordY(0);
        setCoordX((int)Math.random()*(Panel.SCREEN_WIDTH));
    }

    int getCoordX() {
        return coordX;
    }

    void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    int getCoordY() {
        return coordY;
    }

    void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    String getAppearance() {
        return appearance;
    }

    void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    Paint getPaintText() {
        return paintText;
    }


    public void fall(){
        // makes the object fall down the screen when called.
        // calls hitGround(), then Basket.in_range(coord_x), then caught() or missed()
            coordY += fallingSpeed;

    }

    boolean hitGround(){
        //  returns true if the object's at the bottom of the screen when called
        return(coordY == Panel.SCREEN_HEIGHT);
    }

    /**
     * @param canvas     the canvas on which to draw this item.
     * @param appearance the string to draw.
     * @param x          the x-coordinate of the string's cursor location.
     * @param y          the y-coordinate of the string's cursor location.
     */
    void drawString(Canvas canvas, String appearance, int x, int y) {
        canvas.drawText(appearance, x , y, paintText);
    }


    /**
     * @param canvas the canvas on which to draw this item.
     */
    protected void draw(Canvas canvas) {
        drawString(canvas, appearance, coordX, coordY);
    }

    abstract void caught();

    abstract void missed();

}
