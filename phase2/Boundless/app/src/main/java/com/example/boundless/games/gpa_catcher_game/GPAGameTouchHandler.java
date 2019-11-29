package com.example.boundless.games.gpa_catcher_game;

import android.util.Log;
import android.view.MotionEvent;

import com.example.boundless.games.game_utilities.CatcherGameManager;
import com.example.boundless.games.game_utilities.ITouchHandler;
import com.example.boundless.stats.Statistics;

public class GPAGameTouchHandler implements ITouchHandler {


    GPAGameStatus level;
    public GPAGameTouchHandler(CatcherGameManager<GPAGameStatus> manager){
        level = manager.getLevel();
    }

    @Override
    public void screenTouched(MotionEvent event) {
        Statistics.clickEvent();
        Basket basket = level.getBasket();
        double location = ((double)basket.getCoordX() + (double) basket.getSize() / 2.0);
        Log.i("GPAtoucher","Tap location"+event.getX() + " basket Location:" + location);
        if (event.getX() < location)
            basket.moveLeft(level.getBasketSpeed());
        else
            basket.moveRight(level.getBasketSpeed());
    }
}
