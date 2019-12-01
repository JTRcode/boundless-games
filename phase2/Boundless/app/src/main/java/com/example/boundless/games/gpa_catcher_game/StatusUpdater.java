package com.example.boundless.games.gpa_catcher_game;

import android.util.Log;

import com.example.boundless.games.game_utilities.CatcherGameManager;
import com.example.boundless.games.game_utilities.GameResources;
import com.example.boundless.games.gpa_catcher_game.catchers.Basket;
import com.example.boundless.games.gpa_catcher_game.catchers.Catcher;
import com.example.boundless.games.gpa_catcher_game.falling_objects.FallingObject;
import com.example.boundless.games.gpa_catcher_game.falling_objects.FallingObjectFactory;
import com.example.boundless.games.gpa_catcher_game.falling_objects.FallingObjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class StatusUpdater {

    private CatcherGameManager<GPAGameStatus> manager;
    private GPAGameStatus status;

    private static boolean moreSleep;
    private static boolean moreTime;

    private static final String TAG = "StatusUpdater";
    public StatusUpdater(CatcherGameManager<GPAGameStatus> manager){
        status = manager.getLevel();
        this.manager = manager;

        setTools();
    }

    public void update(){
        status.setTime(status.getTime()- GameResources.GPAGAME_DEFAULT_TIME_DECREMENT);
        addFallingObject();
        List<FallingObject> fallingObjects = status.getAllFallingObjects();
        ListIterator<FallingObject> iterator = fallingObjects.listIterator();
        Catcher catcher = status.getCatcher();
        while(iterator.hasNext()){
            FallingObject item = iterator.next();
            if (manager.overlap(catcher, item)) {
                item.caught(status);
                iterator.remove();
                Log.d(TAG, "it is Caught");
            } else if (manager.hitsGround(item)) {
                item.missed(status);
                iterator.remove();
                Log.d(TAG, "it is Missed");
            } else {
                item.fall();
                Log.d(TAG, "Falling");
            }
        }
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
     *  moreSleep and moreTime should be set to false at the start of a new game
     */
    private void setTools(){
        moreSleep = false;
        moreTime = false;
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
