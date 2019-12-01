package com.example.boundless.games.gpa_catcher_game;

import android.util.Log;

import com.example.boundless.games.game_utilities.CatcherGameManager;
import com.example.boundless.games.game_utilities.GameResources;
import com.example.boundless.games.gpa_catcher_game.catchers.Catcher;
import com.example.boundless.games.gpa_catcher_game.falling_objects.FallingObject;
import com.example.boundless.games.gpa_catcher_game.falling_objects.FallingObjectFactory;
import com.example.boundless.games.gpa_catcher_game.falling_objects.FallingObjectsEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Updates the status of the game
 */
public class StatusUpdater {

    private CatcherGameManager<GPAGameStatus> manager;
    private GPAGameStatus status;
    private static boolean moreSleep;
    private static boolean moreTime;

    /**
     * A new status updater for the GPA Catcher game
     *
     * @param manager The manager for the GPA Catcher game
     */
    public StatusUpdater(CatcherGameManager<GPAGameStatus> manager) {
        status = manager.getLevel();
        this.manager = manager;

        setTools();
    }

    /**
     * Updates the items on the screen
     */
    public void update() {
        String tag = "StatusUpdater";
        status.addTime(-GameResources.GPAGAME_DEFAULT_TIME_DECREMENT);
        addFallingObject();
        List<FallingObject> fallingObjects = status.getAllFallingObjects();
        List<FallingObject> fallingObjectsCopy = new ArrayList<>(fallingObjects);
        Catcher catcher = status.getCatcher();
        for (FallingObject object : fallingObjects) {
            if (manager.overlap(catcher, object)) {
                object.caught(status);
                fallingObjectsCopy.remove(object);
                Log.i(tag, "Object was caught");
            } else if (object.hitGround()) {
                object.missed(status);
                fallingObjectsCopy.remove(object);
                Log.i(tag, "Object was missed");
            } else {
                object.fall();
                Log.i(tag, "Object is falling");
            }
        }
        status.setFallingObject(fallingObjectsCopy);
    }

    /**
     * Add a falling object to the screen
     */
    private void addFallingObject() {
        if (status.getAllFallingObjects().size() >=
                GameResources.GPAGAME_MAX_NUMBER_OF_FALLING_OBJECTS)
            return;

        double increaseSleep = 0;
        if (moreSleep){
            increaseSleep = 0.1;
        }
        double increaseTime = 0;
        if (moreTime){
            increaseTime = 0.1;
        }

        double d = Math.random();
        FallingObjectsEnum newItem = null;
        if (d < 0.05) newItem = FallingObjectsEnum.ASSIGNMENT;
        else if (d < 0.06) newItem = FallingObjectsEnum.BOMB;
        else if (d < 0.061 + increaseSleep) newItem = FallingObjectsEnum.SLEEP;
        else if (d < 0.062 + increaseSleep + increaseTime) newItem = FallingObjectsEnum.CLOCK;
        if (newItem != null)
            status.addFallingObject(FallingObjectFactory.createFallingObject(newItem));
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

    /**
     * moreSleep and moreTime should be set to false at the start of a new game
     */
    private static void setTools() {
        moreSleep = false;
        moreTime = false;
    }

}
