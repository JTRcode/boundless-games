package com.example.boundless.games.gpa_catcher_game;

import com.example.boundless.games.game_utilities.GameResources;
import com.example.boundless.games.gpa_catcher_game.catchers.Basket;
import com.example.boundless.games.gpa_catcher_game.catchers.Catcher;
import com.example.boundless.games.gpa_catcher_game.falling_objects.FallingObject;

import java.util.ArrayList;
import java.util.List;

public class GPAGameStatus {
    private int lives;
    private List<FallingObject> fallingObjectList;
    private Catcher catcher;
    private int fallingSpeed;
    private int basketSpeed;
    private double time;
    private double gpa;
    private double decayRate;
    private int maxItems = GameResources.GPAGAME_MAX_NUMBER_OF_FALLING_OBJECTS;

    private static int maxLives;
    private static boolean bombProtection;
    private static int doubleGPA;

    public GPAGameStatus() {
        catcher = new Basket();
        fallingObjectList = new ArrayList<>();
        lives = GameResources.GPAGAME_STARTING_LIVES;
        fallingSpeed = GameResources.GPAGAME_DEFAULT_FALLING_SPEED;
        basketSpeed = GameResources.GPAGAME_DEFAULT_BASKET_SPEED;
        time = GameResources.GPAGAME_MAX_TIME;
        decayRate = GameResources.GPAGAME_DEFAULT_TIME_DECREMENT;
        gpa = GameResources.GPAGAME_STARTING_GPA;

        catcher.setSpeed(basketSpeed);
        FallingObject.setFallingSpeed(fallingSpeed);

        maxLives = GameResources.GPAGAME_STARTING_LIVES;
        bombProtection = false;
        doubleGPA = 0;
    }

    public int getBasketSpeed(){return basketSpeed;}

    public int getMaxItem(){return maxItems;}

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double newGPA) {
        gpa = Math.min(newGPA,0) == 0 ? Math.min(newGPA,4.0) : 0;
    }

    public double getTime() {
        return this.time;
    }

    public void setTime(double time) {
        this.time = Math.min(time, GameResources.GPAGAME_MAX_TIME);
    }

    public int getLives() {
        return this.lives;
    }

    public void setLives(int newLives) {
        lives = Math.min(maxLives, newLives);
    }

    public void addFallingObject(FallingObject newFallingObject) {
        fallingObjectList.add(newFallingObject);
    }

    public Catcher getCatcher() {
        return catcher;
    }

    public void setCatcher(Basket catcher) {
        this.catcher = catcher;
    }

    public List<FallingObject> getAllFallingObjects() {
        return this.fallingObjectList;
    }

    public int getFallingSpeed() {
        return this.fallingSpeed;
    }

    public void setFallingObject(List<FallingObject> fallingObjectsList) {
        this.fallingObjectList = fallingObjectsList;
    }


    /**
     * Increases maximum life by 1 (from 3 to 4) for a level.
     */
    public static void addLife(){
        maxLives = GameResources.MAXIMUM_NUMBER_OF_LIVES;
    }

    /**
     * No damage from the first bomb caught
     */
    public static void bombProtection(){
        bombProtection = true;
    }

    /**
     * Start with a 3.0 GPA
     */
    public static void doubleGPA(){
        doubleGPA = 12;
    }

    /**
     *
     * @return true if bombProtection is true
     */
    public boolean getBombProtection(){
        if (bombProtection){
            bombProtection = false;
            return true;
        }
        return false;
    }

    /**
     * if doubleGPA is in effect, then use it and decrement the number of usage by 1
     * @return true if doubleGPA is still in effect
     */
    public boolean getDoubleGPA(){
        if (doubleGPA > 0){
            doubleGPA -= 1;
            return true;
        }
        return false;
    }

    public int getMaxLives(){
        return maxLives;
    }

}
