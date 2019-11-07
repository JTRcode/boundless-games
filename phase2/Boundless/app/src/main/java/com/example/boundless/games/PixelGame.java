package com.example.boundless.games;

import android.view.MotionEvent;

import com.example.boundless.games.pixel_game.PixelDrawer;
import com.example.boundless.games.pixel_game.PixelTouchHandler;
import com.example.boundless.Panel;
import com.example.boundless.games.pixel_game.PixelManager;
import com.example.boundless.games.pixel_game.PixelOptions;

/**
 * A game where you use pixels to recreate an image.
 */
public class PixelGame extends Game {

    private int currentLevel;
    private static final int START_X = 150;
    private PixelOptions[][] userChoice;
    private PixelManager pixelManager;
    private PixelTouchHandler pixelTouchHandler;
    private PixelDrawer pixelDrawer;

    public PixelGame(int level) {
        currentLevel = level;
        pixelManager = new PixelManager();
        int width = (int) (Panel.SCREEN_WIDTH - START_X * 1.3) / getGridSize();

        userChoice = new PixelOptions[getGridSize()][getGridSize()];
        emptyUserChoices();

        pixelDrawer = new PixelDrawer(width, pixelManager.label(level), START_X);
        pixelTouchHandler = new PixelTouchHandler(userChoice, width, pixelDrawer);
    }

    private void emptyUserChoices() {
        for (int i = 0; i < getGridSize(); i++)
            for (int j = 0; j < getGridSize(); j++)
                userChoice[i][j] = PixelOptions.EMPTY;
    }

    private int getGridSize() {
        return PixelManager.getGridSize(currentLevel);
    }

    /**
     * Check if the game is over
     *
     * @return If all stages have been correctly finished
     */
    @Override
    boolean gameOver() {
        return pixelManager.checkPixels(userChoice, currentLevel);
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
        return "Use the column and row numbers as a guide to create a picture:\n" +
                "each row and column has a number of groupings of pixels (e.g. 10 means 10 pixels in a row in that row or column)";
    }

    @Override
    String getGameOverText() {
        return "GAME OVER!\nYou just finished level " + (currentLevel + 1);
    }

    /**
     * Draw the grid with the pixels, and the labels on the row/column.
     */
    @Override
    public void draw() {
        pixelDrawer.draw(userChoice);
    }
}
