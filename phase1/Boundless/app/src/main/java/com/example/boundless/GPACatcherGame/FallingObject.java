package com.example.boundless.GPACatcherGame;

/**
 * Objects that will fall on the screen.
 */
public abstract class FallingObject {
    /**
     * The coordinates of the object
     */
    Coordinates coordinates;
    /**
     * The appearance of the object
     */
    String appearance;
    /**
     * The speed that the object will fall at
     */
    int fallingSpeed = 2;
    /**
     * The points attributed towards the score when this object is caught
     */
    int pointsWhenCaught;
}
