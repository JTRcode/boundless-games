package com.example.boundless.games;

import android.view.MotionEvent;

import com.example.boundless.games.game_utilities.*;

public class PixelInstructions extends Game {

    private IGridManager pixelManager;
    private ITouchHandler pixelTouchHandler;
    private IGridDrawer pixelDrawer;

    PixelInstructions(GameBuilder builder) {
        this.pixelDrawer = builder.getPixelDrawer();
        this.pixelTouchHandler = builder.getTouchHandler();
        this.pixelManager = builder.getManager();
    }

    @Override
    boolean gameOver() {
        return pixelManager.checkAnswer();
    }

    @Override
    public void setHint() {
        //no hints for instructions
    }

    @Override
    public void screenTouched(MotionEvent event) {
        pixelTouchHandler.screenTouched(event);
    }

    @Override
    public void screenTouched(int x, int y) {
        //not implemented here
    }

    @Override
    String getInstructions() {
        return "";
    }

    @Override
    String getGameOverText() {
        return "Good job! This is the end of the tutorial.\nClick ok to exit.";
    }

    /**
     * Draw the grid with the pixels, and the labels on the row/column.
     */
    @Override
    public void draw() {
        pixelDrawer.draw();
    }
}

