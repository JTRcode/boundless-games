package com.example.boundless.games.gpa_catcher_game;

import com.example.boundless.games.game_utilities.GameResources;
import com.example.boundless.games.gpa_catcher_game.catchers.Basket;
import com.example.boundless.games.gpa_catcher_game.catchers.Catcher;
import com.example.boundless.games.gpa_catcher_game.falling_objects.FallingObject;

import java.util.ArrayList;
import java.util.List;

/**
 * The current status of a GPA Catcher game
 */
public class GPAGameStatus {
    private int lives;
    private List<FallingObject> fallingObjectList;
    private Catcher catcher;
    private double time;
    private double gpa;
    private static int maxLives = GameResources.MAXIMUM_NUMBER_OF_LIVES;
    private static boolean bombProtection = false;
    private static int doubleGPA = 0;

    /**
     * A new status for the GPA Catcher game
     */
    public GPAGameStatus() {
        catcher = new Basket();
        fallingObjectList = new ArrayList<>();
        lives = GameResources.GPAGAME_STARTING_LIVES;
        time = GameResources.GPAGAME_MAX_TIME;
        gpa = GameResources.GPAGAME_STARTING_GPA;

        resetStatus();
    }

    /**
     * Get the current GPA of the game
     *
     * @return The current GPA
     */
    public double getGpa() {
        return gpa;
    }

    /**
     * Add to the current GPA amount
     *
     * @param additionalGpa The amount to add to the GPA
     */
    public void addGpa(double additionalGpa) {
        double newGpa = gpa + additionalGpa;
        gpa = Math.min(newGpa, 0) == 0 ? Math.min(newGpa, 4.0) : 0;
    }

    /**
     * Get the amount of time left in the game
     *
     * @return The amount of time left in the game
     */
    public double getTime() {
        if (time <= 0) resetStatus();
        return time;
    }

    /**
     * Add to the current amount of time
     *
     * @param additionalTime The amount to add to the time
     */
    public void addTime(double additionalTime) {
        time = Math.min(time + additionalTime, GameResources.GPAGAME_MAX_TIME);
    }

    /**
     * Get the current amount of lives
     *
     * @return The amount of lives left
     */
    int getLives() {
        if (lives <= 0) resetStatus();
        return lives;
    }

    /**
     * Add to the current amount of lives
     *
     * @param additionalLives The amount of lives to add
     */
    public void addLives(int additionalLives) {
        lives = Math.max(0, lives + additionalLives);
    }

    /**
     * Gets the basket that catches falling items
     *
     * @return The basket
     */
    public Catcher getCatcher() {
        return catcher;
    }

    /**
     * Sets the catcher that will catch falling items
     *
     * @param catcher The catcher to use
     */
    public void setCatcher(Catcher catcher) {
        this.catcher = catcher;
    }

    /**
     * Adds a falling object to the screen
     *
     * @param newFallingObject The falling object to add
     */
    void addFallingObject(FallingObject newFallingObject) {
        fallingObjectList.add(newFallingObject);
    }

    /**
     * Gets a list of the falling objects on the screen
     *
     * @return A list of falling objects on the screen
     */
    List<FallingObject> getAllFallingObjects() {
        return fallingObjectList;
    }

    /**
     * Set the list of falling objects
     *
     * @param fallingObjectsList The list of falling objects to keep track of
     */
    void setFallingObject(List<FallingObject> fallingObjectsList) {
        this.fallingObjectList = fallingObjectsList;
    }


    /**
     * Increases maximum life by 1 (from 3 to 4) for a level.
     */
    public static void addToMaxLives() {
        maxLives++;
    }

    /**
     * No damage from the first bomb caught
     */
    public static void bombProtection() {
        bombProtection = true;
    }

    /**
     * Start with a 3.0 GPA
     */
    public static void doubleGPA() {
        doubleGPA = 12;
    }

    /**
     * Tell if bomb protection is in effect
     *
     * @return true if bombProtection is true
     */
    public boolean getBombProtection() {
        if (bombProtection) {
            bombProtection = false;
            return true;
        }
        return false;
    }

    /**
     * Tell if the doubleGPA hint is still in effect
     *
     * @return true if doubleGPA is still in effect
     */
    public boolean getDoubleGPA() {
        if (doubleGPA > 0) {
            doubleGPA -= 1;
            return true;
        }
        return false;
    }

    /**
     * Get the maximum number of lives
     *
     * @return The maximum number of lives
     */
    int getMaxLives() {
        return maxLives;
    }

    private static void resetStatus() {
        maxLives = GameResources.MAXIMUM_NUMBER_OF_LIVES;
        bombProtection = false;
        doubleGPA = 0;
    }
}
