package com.example.boundless.games;

import android.view.MotionEvent;

import com.example.boundless.games.game_utilities.CatcherGameManager;
import com.example.boundless.games.game_utilities.GameResources;
import com.example.boundless.games.game_utilities.IGameDrawer;
import com.example.boundless.games.game_utilities.ITouchHandler;
import com.example.boundless.games.gpa_catcher_game.StatusUpdater;
import com.example.boundless.shop.GpaShop;

/**
 * A GPA catcher game, where you catch falling objects to get a good grade!
 */
public class GPACatcherGameFacade extends Game {

    private CatcherGameManager manager;
    private ITouchHandler touchHandler;
    private IGameDrawer drawer;
    private StatusUpdater statusUpdater;

    /**
     * A new GPA game
     *
     * @param builder The builder to create the GPA game
     */
    GPACatcherGameFacade(GameBuilder builder) {
        manager = (CatcherGameManager) builder.getManager();
        drawer = builder.getDrawer();
        touchHandler = builder.getTouchHandler();
        statusUpdater = builder.getStatusUpdater();
    }

    @Override
    String getInstructions() {
        return GameResources.getGPAGameInstructions();
    }

    @Override
    public void update() {
        statusUpdater.update();
    }

    @Override
    boolean gameOver() {
        return manager.checkGameOver();
    }

    @Override
    public void draw() {
        GPAGameDrawer.draw();
    }

    @Override
    public void screenTouched(MotionEvent event) {
        GPAGameTouchHandler.screenTouched(event);
    }

    @Override
    String getGameOverText() {
        return manager.getGameOverText();
    }
}
