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

    private CatcherGameManager GPAGameManager;
    private ITouchHandler GPAGameTouchHandler;
    private IGameDrawer GPAGameDrawer;
    private StatusUpdater GPAStatusUpdater;

    public GPACatcherGameFacade(GameBuilder builder) {
        this.GPAGameManager = (CatcherGameManager) builder.getManager();
        this.GPAGameDrawer = builder.getDrawer();
        this.GPAGameTouchHandler = builder.getTouchHandler();
        this.GPAStatusUpdater = builder.getStatusUpdater();
    }

    /**
     *  if shopInventory has items then use them automatically
     */

    @Override
    String getInstructions() {
        return GameResources.getGPAGameInstructions();
    }

    @Override
    public void update(){
        GPAStatusUpdater.update();
    }

    @Override
    boolean gameOver() {
        return GPAGameManager.checkGameOver();
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
        return GPAGameManager.getGameOverText();
    }
}
