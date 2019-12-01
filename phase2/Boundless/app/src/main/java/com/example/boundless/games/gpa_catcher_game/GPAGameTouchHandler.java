package com.example.boundless.games.gpa_catcher_game;

import android.util.Log;
import android.view.MotionEvent;

import com.example.boundless.games.game_utilities.CatcherGameManager;
import com.example.boundless.games.game_utilities.ITouchHandler;
import com.example.boundless.games.gpa_catcher_game.catchers.Catcher;
import com.example.boundless.stats.Statistics;

/**
 * A touch handler for the GPA Catcher game
 */
public class GPAGameTouchHandler implements ITouchHandler {

    private GPAGameStatus status;

    /**
     * A new touch handler for the GPA game
     *
     * @param manager The manager managing the game
     */
    public GPAGameTouchHandler(CatcherGameManager<GPAGameStatus> manager) {
        status = manager.getLevel();
    }

    /**
     *  move the basket and make sure it's inside the screen while moving
     * @param event The motion event to handle
     */
    @Override
    public void screenTouched(MotionEvent event) {
        Statistics.clickEvent();
        Catcher catcher = status.getCatcher();
        double location = ((double) catcher.getCoordX() + (double) catcher.getSize() / 2.0);
        Log.i(this.getClass().toString(), "Tap location" + event.getX() + " basket Location:" + location);

        if (event.getX() < location) catcher.moveLeft();
        else catcher.moveRight();
    }
}
