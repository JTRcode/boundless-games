package com.example.boundless.games.gpa_catcher_game;

import com.example.boundless.games.game_utilities.GameResources;
import com.example.boundless.games.gpa_catcher_game.falling_objects.FallingObject;

import java.util.ArrayList;
import java.util.List;

public class GPAGameStatus {
    private int lives;
    private List<FallingObject> fallingObjectList;
    private Basket basket;
    private int fallingSpeed;
    private int basketSpeed;
    private double time;
    private double gpa;
    private double decayRate;
    private int maxItems = GameResources.GPAGAME_MAX_NUMBER_OF_FALLING_OBJECTS;

    private static boolean addLife = false;
    private static boolean bombProtection = false;
    private static int doubleGPA = 0;

    public GPAGameStatus() {
        basket = new Basket();
        fallingObjectList = new ArrayList<>();
        lives = GameResources.GPAGAME_STARTING_LIVES;
        if (addLife){
            lives += 1;
            addLife = false;
        }

        fallingSpeed = GameResources.GPAGAME_DEFAULT_FALLING_SPEED;
        basketSpeed = GameResources.GPAGAME_DEFAULT_BASKET_SPEED;
        time = GameResources.GPAGAME_MAX_TIME;
        decayRate = GameResources.GPAGAME_DEFAULT_TIME_DECREMENT;
        gpa = GameResources.GPAGAME_STARTING_GPA;
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
        lives = newLives;
    }

    public void addFallingObject(FallingObject newFallingObject) {
        fallingObjectList.add(newFallingObject);
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
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
     * Increases life by 1 (from 3 to 4) for a level.
     */
    public static void addLife(){
        addLife = true;
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
     *
     * @return true if doubleGPA is still in effect
     */
    public boolean getDoubleGPA(){
        if (doubleGPA > 0){
            doubleGPA -= 1;
            return true;
        }
        return false;
    }
}
