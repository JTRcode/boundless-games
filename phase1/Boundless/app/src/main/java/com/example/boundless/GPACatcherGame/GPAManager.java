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
                System.out.println("it is Caught");
            } else if (temp.hitGround()) {
                temp.missed();
                System.out.println("it is Missed");
                iterator.remove();
            } else
                temp.fall();
                System.out.println("Falling");
        }
    }

    /**
     * Add a falling object to the screen
     */
    public void addFallingObject() {
        double d = Math.random();
        if (d < 0.02)
            fallingObjects.add(new Assignment());
        else if (d < 0.025)
            fallingObjects.add(new Bomb());
        else if (d < 0.026)
            fallingObjects.add(new Sleep());
        else if (d < 0.027)
            fallingObjects.add(new Clock());
    }

}