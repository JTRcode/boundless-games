package com.example.boundless.GPACatcherGame;

/**
 * A basket for catching falling objects
 */
public class Basket {
    /**
     * The appearance of the basket
     */
    private String appearance;
    /**
     * The location of the basket
     */
    private int coord_x;
    private int coord_y;

    /**
     * the distance basket moves by each tap
     * the value will depend on the size of basket and falling objects
     */
    private int speed;


    /**
     * how much distance the basket can reach
     * the range in which objects are considered caught
     */
    private int range;

    /**
     * the width of the screen
     */
    private int screenWidth;

    public Basket(int screenWidth, int screenHeight) {
        appearance = "|____|";
        this.screenWidth = screenWidth;
        coord_x = screenWidth/2;
        coord_y = screenHeight-5;
    }

    /**
     * Moves the basket left on the screen
     */
    void moveLeft() {
        if(coord_x !=0)
            coord_x -= speed;
    }

    /**
     * Moves the basket right on the screen
     */
    void moveRight() {

        if(coord_x+appearance.length()<screenWidth)
            coord_x +=speed;
    }

    boolean inRange(FallingObject object){
        //  calls only when a falling object reaches the same y value as basket;
        //  returns true if the object is in range. i.e. if the object is "caught"
            int right = object.getCoord_x()+object.getAppearance().length();
            int left = object.getCoord_x();
            if(left>=coord_x&&right<=coord_x+appearance.length())
                return true;
        return false;
    }

}
