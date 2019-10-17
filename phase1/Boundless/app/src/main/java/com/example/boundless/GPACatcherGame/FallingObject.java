package com.example.boundless.GPACatcherGame;

/**
 * Objects that will fall on the screen.
 */
public abstract class FallingObject {

    private int coord_x; //The first coordinate of the object
    private int coord_y; //The second coordinate of the object
    static int height; // height of the screen; used to indicate if the object hits the ground
    private String appearance; //The appearance of the object
    private int fallingSpeed = 2; //The speed that the object will fall at

    public int getCoord_x() {
        return coord_x;
    }

    public void setCoord_x(int coord_x) {
        this.coord_x = coord_x;
    }

    public int getCoord_y() {
        return coord_y;
    }

    public void setCoord_y(int coord_y) {
        this.coord_y = coord_y;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public void fall(){
        // makes the object fall down the screen when called.
        // calls hitGround(), then Basket.in_range(coord_x), then caught() or missed()
        //TODO compare the Range and if hit the ground in GPAManager to remove objects
            coord_y += fallingSpeed;

    }

    boolean hitGround(){
        //  returns true if the object's at the bottom of the screen when called
            if(coord_y == height)
                return true;
        return false;
    }

    abstract void caught();
        //TODO sub classes calls static addLife, addGpa, and/or addTime methods in GPACatcherGame
        // with positive values

    abstract void missed();
    //TODO sub classes calls static addLife, addGpa, and/or addTime methods in GPACatcherGame
    // with negative values

}
