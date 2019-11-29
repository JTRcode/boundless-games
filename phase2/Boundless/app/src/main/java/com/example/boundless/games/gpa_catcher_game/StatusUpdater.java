package com.example.boundless.games.gpa_catcher_game;

import android.util.Log;

import com.example.boundless.games.game_utilities.CatcherGameManager;
import com.example.boundless.games.game_utilities.GameResources;
import com.example.boundless.games.gpa_catcher_game.falling_objects.FallingObject;
import com.example.boundless.games.gpa_catcher_game.falling_objects.FallingObjectFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatusUpdater {

    private CatcherGameManager<GPAGameStatus> manager;
    private GPAGameStatus status;

    private static boolean moreSleep;
    private static boolean moreTime;

    private static final String TAG = "StatusUpdater";
    public StatusUpdater(CatcherGameManager<GPAGameStatus> manager){
        status = manager.getLevel();
        this.manager = manager;
    }

    public void update(){
        status.setTime(status.getTime()- GameResources.GPAGAME_DEFAULT_TIME_DECREMENT);
        addFallingObject();
        List<FallingObject> fallingObjects = status.getAllFallingObjects();
        List<FallingObject> fallingObjectsCopy = new ArrayList<>(fallingObjects);
        Basket basket = status.getBasket();
        for (FallingObject object : fallingObjects){
            if (manager.overlap(basket, object)) {
                //TODO pass in level so caught can use it
                object.caught(status);
                fallingObjectsCopy.remove(object);
                Log.d(TAG, "it is Caught");
            } else if (manager.hitsGround(object)) {
                //TODO pass in level so caught can use it
                object.missed(status);
                fallingObjectsCopy.remove(object);
                Log.d(TAG, "it is Missed");
            } else {
                object.fall();
                Log.d(TAG, "Falling");
            }
        }
        status.setFallingObject(fallingObjectsCopy);
    }

    /**
     * Add a falling object to the screen
     */
    public void addFallingObject() {

        if (status.getAllFallingObjects().size() >= status.getMaxItem()) return;
        double increaseSleep = 0;
        if (moreSleep){
            increaseSleep = 0.1;
        }
        double increaseTime = 0;
        if (moreTime){
            increaseTime = 0.1;
        }

        double d = Math.random();
        FallingObjects newItem = null;
        if (d < 0.05) newItem = FallingObjects.ASSIGNMENT;
        else if (d < 0.06) newItem = FallingObjects.BOMB;
        else if (d < 0.061 + increaseSleep) newItem = FallingObjects.SLEEP;
        else if (d < 0.062 + increaseSleep + increaseTime) newItem = FallingObjects.CLOCK;
        if (newItem != null){
            status.addFallingObject(FallingObjectFactory.createFallingObject(newItem));
        }
    }

    /**
     * Increased drop rate for sleep
     */
    public static void moreSleep(){
        moreSleep = true;
    }

    /**
     * Increased drop rate for time
     */
    public static void moreTime(){
        moreTime = true;
    }

}
