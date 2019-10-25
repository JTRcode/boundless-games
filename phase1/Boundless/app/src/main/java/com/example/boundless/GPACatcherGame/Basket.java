package com.example.boundless.GPACatcherGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.boundless.Panel;
import com.example.boundless.R;

/**
 * A basket for catching falling objects
 */
class Basket {
    //TODO: Finish javadocs
    /**
     * The appearance of the basket
     */
    private Bitmap appearance;
    private Paint paintText = new Paint();
    /**
     * The location of the basket
     */
    private int coordX;
    private int coordY;

    /**
     * the distance basket moves by each tap
     * the value will depend on the size of basket and falling objects
     */
    private int speed;


//    /**
//     * how much distance the basket can reach
//     * the range in which objects are considered caught
//     */
//    private int range;

     /**
     * the width of the screen
     */
    private int screenWidth;
    private int screenHeight;

    public Basket(int speed) {
        paintText.setTextSize(36);
        paintText.setColor(Color.GRAY);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);

        appearance = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.basket);
//        appearance = "|____|";
        this.screenWidth = Panel.SCREEN_WIDTH;
        this.screenHeight = Panel.SCREEN_HEIGHT;
        setCoordX(screenWidth/2);
        setCoordY(screenHeight-30);
        this.speed = speed;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    /**
     * Moves the basket left on the screen
     */
    void moveLeft() {
        if(coordX !=0)
            coordX -= speed;
    }

    /**
     * Moves the basket right on the screen
     */
    void moveRight() {
        coordX += speed;
//        if(coordX +appearance.length()<screenWidth)
//            coordX +=speed;
    }

    //  calls only when a falling object reaches the same y value as basket;
    boolean inRange(FallingObject object){
        //  returns true if the object is in range. i.e. if the object is "caught"
            int right = object.getCoordX()+object.getAppearance().length();
            int left = object.getCoordX();
            int bottom = object.getCoordY();

//            return (left>= coordX &&right<= coordX +appearance.length()&& bottom>=coordY);
            return false;
    }

//    /**
//     * @param canvas     the canvas on which to draw this item.
//     * @param appearance the string to draw.
//     * @param x          the x-coordinate of the string's cursor location.
//     * @param y          the y-coordinate of the string's cursor location.
//     */
//    void drawString(Canvas canvas, String appearance, int x, int y) {
//        canvas.drawText(appearance, x, y, paintText);
//    }

    /**
     * Draws this fish tank item.
     *
     * @param canvas the canvas on which to draw this item.
     */
    public void draw(Canvas canvas) {
//        drawString(canvas, appearance, coordX, coordY);
        canvas.drawBitmap(appearance, coordX, coordY, paintText);

    }
    //TODO: the javadoc for draw... (and inRange javadoc needs to be implemented)

}
