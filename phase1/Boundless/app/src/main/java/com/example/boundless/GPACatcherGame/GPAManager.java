package com.example.boundless.GPACatcherGame;

import android.graphics.Canvas;


import java.util.*;

/**
 * Creates new falling objects and updates them
 */
public class GPAManager {
    //TODO: make sure javadocs are there, and change modifiers to package-private or private where possible
    /**
     * A list of all the falling objects on the screen
     */
    LinkedList<FallingObject> fallingObjects = new LinkedList<>();
    Basket basket;
    private int num_items;
    private static double add_chance;
    static int max_items = 10;


    /**
     * Draw all the falling objects on the screen
     */
    public void draw(Canvas canvas) {
        for (FallingObject f : fallingObjects) {
            f.draw(canvas);
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
                num_items--;
                System.out.println("it is Caught");
            } else if (temp.hitGround()) {
                temp.missed();
                System.out.println("it is Missed");
                iterator.remove();
                num_items--;
            } else
                temp.fall();
                System.out.println("Falling");
        }
    }

    /**
     * Add a falling object to the screen
     */
    public void addFallingObject() {
        switch (num_items){
            case 0:
                add_chance = 0.1;
                break;
            case 1:
                add_chance = 0.05;
                break;
            case 2:
                add_chance = 0.02;
                break;
            case 3:
                add_chance = 0.01;
                break;
            default:
                add_chance = 0;

        }
        if (num_items >= max_items){
            return;
        }

        double d = Math.random();
        if (d < 0.02 + add_chance){
            fallingObjects.add(new Assignment());
            num_items++;
        }
        else if (d < 0.025 + add_chance){
            fallingObjects.add(new Bomb());
            num_items++;
        }
        else if (d < 0.026 + add_chance){
            fallingObjects.add(new Sleep());
            num_items++;
        }
        else if (d < 0.027 + add_chance){
            fallingObjects.add(new Clock());
            num_items++;
        }
    }

}