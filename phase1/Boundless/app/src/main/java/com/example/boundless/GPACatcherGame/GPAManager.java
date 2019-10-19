package com.example.boundless.GPACatcherGame;

import android.graphics.Canvas;


import java.util.*;

/**
 * Creates new falling objects and updates them
 */
public class GPAManager {
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
            }else if(temp.hitGround()){
                temp.missed();
                iterator.remove();
            }else
                temp.fall();
        }


    }

    /**
     * Add a falling object to the screen
     */
    public void addFallingObject() {
        double d = Math.random();
        if (d < 0.45)
            fallingObjects.add(new Assignment());
        else if (d < 0.7)
            fallingObjects.add(new Bomb());
        else if (d < 0.85)
            fallingObjects.add(new Sleep());
        else
            fallingObjects.add(new Clock());

    }

//    /**
//     * Remove a falling object from the screen
//     */
//    public void removeFallingObject() {
//        //TODO
//    }
//}

}