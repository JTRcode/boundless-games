package com.example.boundless.games.gpa_catcher_game.falling_objects;

/**
 * Creates new falling objects
 */
public class FallingObjectFactory {

    private FallingObjectFactory() {
    }

    /**
     * Creates a new falling object
     *
     * @param objectEnum The enum of the falling object to create
     * @return The new falling object
     */
    public static FallingObject createFallingObject(FallingObjectsEnum objectEnum) {
        switch (objectEnum) {
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
