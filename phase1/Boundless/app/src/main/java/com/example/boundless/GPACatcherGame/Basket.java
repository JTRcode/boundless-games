package com.example.boundless.GPACatcherGame;

/**
 * A basket for catching falling objects
 */
public class Basket {
    /**
     * The appearance of the basket
     */
    String appearance;
    /**
     * The location of the basket
     */
    int coord_x;
    int coord_y;
    int range; // how much distance the basket can reach;
                // the range in which objects are considered caught

    public Basket() {
        appearance = "|__|";
    }

    /**
     * Moves the basket left on the screen
     */
    public void moveLeft() {
        //TODO
    }

    /**
     * Moves the basket right on the screen
     */
    public void moveRight() {
        //TODO
    }

    public static boolean in_range(int object_x_coord){
        // TODO calls only when a falling object hits bottom;
        //  returns true if the object is in range. i.e. if the object is "caught"
        return false;
    }
}
