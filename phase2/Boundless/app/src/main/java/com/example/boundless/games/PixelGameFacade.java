package com.example.boundless.games;

import android.view.MotionEvent;

import com.example.boundless.games.game_utilities.*;

/**
 * A game where you use pixels to recreate an image.
 */
public class PixelGameFacade extends Game {

    private int currentLevel;
    private IGridManager pixelManager;
    private ITouchHandler pixelTouchHandler;
    private IGridDrawer pixelDrawer;

    /**
     * A new pixel game
     *
     * @param builder The builder building the pixel game
     */
    PixelGameFacade(GameBuilder builder) {
        currentLevel = builder.getLevel();
        this.pixelDrawer = builder.getPixelDrawer();
        this.pixelTouchHandler = builder.getTouchHandler();
        this.pixelManager = builder.getManager();
    }

    /**
     * Check if the game is over
     *
     * @return If all stages have been correctly finished
     */
    @Override
    boolean gameOver() {
        return pixelManager.checkAnswer();
    }

    @Override
    public void screenTouched(MotionEvent event) {
        pixelTouchHandler.screenTouched(event);
    }

    @Override
    public void screenTouched(int x, int y) {
    }

    @Override
    String getInstructions() {
        return GameResources.getPixelInstructions();
    }

    @Override
    String getGameOverText() {
        return GameResources.getPixelGameOver(currentLevel + 1);
    }

    /**
     * Draw the grid with the pixels, and the labels on the row/column.
     */
    @Override
    public void draw() {
        pixelDrawer.draw();
    }
}
