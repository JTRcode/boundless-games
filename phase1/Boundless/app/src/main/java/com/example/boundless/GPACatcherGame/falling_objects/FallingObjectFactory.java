package com.example.boundless.GPACatcherGame.falling_objects;

import com.example.boundless.GPACatcherGame.FallingObjects;

/**
 * Creates new falling objects
 */
public class FallingObjectFactory {

    private FallingObjectFactory() {
    }

    /**
     * Creates a new falling object
     *
     * @param object The enum of the falling object to create
     * @return The new falling object
     */
    public static FallingObject createFallingObject(FallingObjects object) {
        switch (object) {
            case BOMB:
                return new Bomb();
            case CLOCK:
                return new Clock();
            case ASSIGNMENT:
                return new Assignment();
            case SLEEP:
                return new Sleep();
            default:
                return null;
        }
    }
}
