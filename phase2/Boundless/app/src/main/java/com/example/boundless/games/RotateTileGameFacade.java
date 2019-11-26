package com.example.boundless.games;

import android.view.MotionEvent;

import com.example.boundless.games.game_utilities.*;
import com.example.boundless.games.rotate_tile_game.RotateTileTouchHandler;
import com.example.boundless.stats.Statistics;

/**
 * A game where you rotate tiles to get from point A to point B.
 */
public class RotateTileGameFacade extends Game {

    private int currentLevel;
    private GridManager manager;
    private ITouchHandler touchHandler;
    private IGridDrawer drawer;

    /**
     * A new rotate tile game
     *
     * @param builder THe builder building the tile game
     */
    public RotateTileGameFacade(GameBuilder builder) {
        currentLevel = builder.getLevel();
        touchHandler = builder.getTouchHandler();
        drawer = builder.getDrawer();
        manager = builder.getManager();
    }

    @Override
    boolean gameOver() {
        boolean gameIsOver = manager.checkAnswer();
        if (gameIsOver) {
            Statistics.endTimeByGame(GamesEnum.ROTATETILE);}
        return manager.checkAnswer();
    }

    @Override
    public void setHint() {
        RotateTileTouchHandler.setFreeTile('X');
    }

    @Override
    public void screenTouched(MotionEvent event) {
        touchHandler.screenTouched(event);
    }

    @Override
    public void screenTouched(int x, int y) {
        //not implemented here
    }

    @Override
    String getInstructions() {
        return GameResources.getRotateTileInstructions();
    }

    @Override
    String getGameOverText() {
        return GameResources.getRotateTileGameOver(currentLevel + 1);
    }

    @Override
    public void draw() {
        drawer.draw();
    }
}
