package com.example.boundless.games.gpa_catcher_game;

import android.util.Log;
import android.view.MotionEvent;

import com.example.boundless.Panel;
import com.example.boundless.games.game_utilities.CatcherGameManager;
import com.example.boundless.games.game_utilities.ITouchHandler;
import com.example.boundless.games.gpa_catcher_game.catchers.Basket;
import com.example.boundless.games.gpa_catcher_game.catchers.Catcher;
import com.example.boundless.stats.Statistics;

public class GPAGameTouchHandler implements ITouchHandler {


    GPAGameStatus level;
    public GPAGameTouchHandler(CatcherGameManager<GPAGameStatus> manager){
        level = manager.getLevel();
    }

    /**
     *  move the basket and make sure it's inside the screen while moving
     * @param event The motion event to handle
     */
    @Override
    public void screenTouched(MotionEvent event) {
        Statistics.clickEvent();
        Catcher catcher = level.getCatcher();
        double location = ((double)catcher.getCoordX() + (double) catcher.getSize() / 2.0);
        Log.i("GPAtoucher","Tap location"+event.getX() + " basket Location:" + location);
        if (event.getX() < location) {
            if (catcher.getCoordX()>0)
                catcher.moveLeft();
        } else if (catcher.getCoordX() + catcher.getSize() < Panel.SCREEN_WIDTH)
            catcher.moveRight();

    }
}
