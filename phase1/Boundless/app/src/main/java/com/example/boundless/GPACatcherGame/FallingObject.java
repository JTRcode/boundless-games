package com.example.boundless.GPACatcherGame;

/**
 * Objects that will fall on the screen.
 */
public abstract class FallingObject {

    int coord_x; //The first coordinate of the object
    int coord_y; //The second coordinate of the object
    static int height; // height of the screen; used to indicate if the object hits the ground
    String appearance; //The appearance of the object
    int fallingSpeed = 2; //The speed that the object will fall at

    public void fall(){
        //TODO makes the object fall down the screen when called.
        // calls hit_ground(), then Basket.in_range(coord_x), then caught() or missed()
    }

    public boolean hit_ground(){
        // TODO returns true if the object's at the bottom of the screen when called
        return false;
    }

    public abstract void caught();
        //TODO sub classes calls static addLife, addGpa, and/or addTime methods in GPACatcherGame
        // with positive values

    public abstract void missed();
    //TODO sub classes calls static addLife, addGpa, and/or addTime methods in GPACatcherGame
    // with negative values

}
