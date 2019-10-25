package com.example.boundless.GPACatcherGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.boundless.Panel;

/**
 * Objects that will fall on the screen.
 */
public abstract class FallingObject {
    //TODO: javadocs need to be created
    private int coordX; //The first coordinate of the object
    private int coordY; //The second coordinate of the object
    Bitmap appearance; //The appearance of the object
    private int size = 100;
    private int fallingSpeed = 8; //The speed that the object will fall at
    private Paint paintText = new Paint();

    public FallingObject(){
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        setCoordY(0);
        int x = (int) (Math.random()*Panel.SCREEN_WIDTH-size);
        setCoordX(x);
    }
    public void setSize(int size){
        this.size = size;
    }
    public int getSize(){
        return this.size;
    }

    int getCoordX() {
        return coordX;
    }

    private void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    int getCoordY() {
        return coordY;
    }

    private void setCoordY(int coordY) {
        this.coordY = coordY;
    }


    /**
     *  makes the object fall down the screen when called.
     */
    void fall(){

            coordY += fallingSpeed;
    }

    boolean hitGround(){
        //  returns true if the object's at the bottom of the screen when called
        return(coordY >= Panel.SCREEN_HEIGHT);
    }


    /**
     * @param canvas the canvas on which to draw this item.
     */
    protected void draw(Canvas canvas) {
//        drawString(canvas, appearance, coordX, coordY);
        canvas.drawBitmap(appearance, coordX, coordY, paintText);
    }

    abstract void caught();

    abstract void missed();

}
