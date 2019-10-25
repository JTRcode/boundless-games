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
        //Todo   basket move right or left
        ListIterator<FallingObject> iterator = fallingObjects.listIterator();
        while (iterator.hasNext()) {
            FallingObject temp = iterator.next();
            if (basket.inRange(temp)) {
                temp.caught();
                iterator.remove();
            } else if (temp.hitGround()) {
                temp.missed();
                iterator.remove();
            } else
                temp.fall();
        }
    }

    /**
     * Add a falling object to the screen
     */
    public void addFallingObject() {
        //TODO: figure out why objects aren't showing up
        double d = Math.random();
        if (d < 0.03)
            fallingObjects.add(new Assignment());
        else if (d < 0.04)
            fallingObjects.add(new Bomb());
        else if (d < 0.045)
            fallingObjects.add(new Sleep());
        else if (d < 0.05)
            fallingObjects.add(new Clock());

    }

//    /**
//     * Remove a falling object from the screen
//     */
//    public void removeFallingObject() {
//        //TODO: remove this if its not being used
//    }
//}

}