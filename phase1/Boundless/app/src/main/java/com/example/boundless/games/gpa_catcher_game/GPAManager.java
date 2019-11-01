package com.example.boundless.games.gpa_catcher_game;

import android.util.Log;

import com.example.boundless.games.gpa_catcher_game.falling_objects.FallingObject;
import com.example.boundless.games.gpa_catcher_game.falling_objects.FallingObjectFactory;

import java.util.*;

/**
 * Creates new falling objects and updates them
 */
public class GPAManager {

    public Basket basket;
    private LinkedList<FallingObject> fallingObjects = new LinkedList<>();
    private int numItems;
    private int maxItems = 10;
    private static final String TAG = "GPAManager";

    public GPAManager(int speed){
        basket = new Basket(speed);
    }
    /**
     * Draw all the falling objects on the screen
     */
    public void draw() {
        for (FallingObject f : fallingObjects) {
            f.draw();
        }
    }

    /**
     * Update the location of the objects on the screen
     */
    public void update() {
        ListIterator<FallingObject> iterator = fallingObjects.listIterator();
        while (iterator.hasNext()) {
            FallingObject temp = iterator.next();
            if (basket.inRange(temp)) {
                temp.caught();
                iterator.remove();
                numItems--;
                Log.d(TAG, "it is Caught");
            } else if (temp.hitGround()) {
                temp.missed();
                Log.d(TAG, "it is Missed");
                iterator.remove();
                numItems--;
            } else {
                temp.fall();
                Log.d(TAG, "Falling");
            }
        }
    }

    public void setMaxItems(int num){
        this.maxItems = num;
    }

    /**
     * Add a falling object to the screen
     */
    public void addFallingObject() {
        double addChance;
        switch (numItems){
            case 0:
                addChance = 0.1;
                break;
            case 1:
                addChance = 0.05;
                break;
            case 2:
                addChance = 0.02;
                break;
            case 3:
                addChance = 0.01;
                break;
            default:
                addChance = 0;

        }
        if (numItems >= maxItems) return;

        double d = Math.random();
        FallingObjects newItem = null;
        if (d < 0.02 + addChance) newItem = FallingObjects.ASSIGNMENT;
        else if (d < 0.025 + addChance) newItem = FallingObjects.BOMB;
        else if (d < 0.026 + addChance) newItem = FallingObjects.SLEEP;
        else if (d < 0.027 + addChance) newItem = FallingObjects.CLOCK;
        if (newItem != null){
            fallingObjects.add(FallingObjectFactory.createFallingObject(newItem));
            numItems++;
        }
    }

}