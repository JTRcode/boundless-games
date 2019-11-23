package com.example.boundless.games.pixel_game;

import android.view.MotionEvent;

import com.example.boundless.games.game_utilities.*;
import com.example.boundless.stats.Achievements;
import com.example.boundless.stats.Statistics;

/**
 * Handles the touch input to the pixel game
 */
public class PixelTouchHandler implements ITouchHandler {

    private int newI = -1;
    private int newJ = -1;
    private int oldI = -1;
    private int oldJ = -1;
    private int startX = GameResources.PIXEL_START_X;
    private int startY = GameResources.PIXEL_START_Y;
    private int width;
    private int gridSize;
    private PixelOptions firstChangedOption;
    private PixelOptions[][] userChoices;

    /**
     * Create a new touch handler for the pixel game
     *
     * @param manager The manager for the pixel game
     */
    public PixelTouchHandler(GridManager<PixelOptions, PixelLevel> manager) {
        width = manager.getLevel().getWidth(startX);
        userChoices = manager.getUserChoices();
        this.gridSize = userChoices.length;
    }

    @Override
    public void screenTouched(MotionEvent event) {
        if (isTripleTap(event)) PixelDrawer.showHint();

        int[] oldCoords;

        switch (event.getAction()) {
            case (MotionEvent.ACTION_MOVE):
                if (event.getHistorySize() < 1) return;
                oldCoords = convertCoordToIJ((int) event.getHistoricalX(0), (int) event.getHistoricalY(0));
                break;
            case (MotionEvent.ACTION_DOWN):
                oldCoords = convertCoordToIJ((int) event.getX(0), (int) event.getY(0));
                break;
            default:
                oldI = -1;
                return;
        }
        if (!changeIfCoords(oldCoords, event.getAction() == MotionEvent.ACTION_DOWN))
            newI = newJ = -1;
    }

    private boolean isTripleTap(MotionEvent event) {
        int action = event.getAction();
        int count = 0;
        if ((action & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_DOWN)
            count = event.getPointerCount();
        return count == 3;
    }

    private boolean changeIfCoords(int[] coordsIJ, boolean actionDown) {
        newI = coordsIJ[0];
        newJ = coordsIJ[1];
        if ((newI != oldI || newJ != oldJ) && isInBoundsIJ(newI, newJ)) {
            if (actionDown) firstChangedOption = switchPixel(newI, newJ);
            else switchPixel(newI, newJ, firstChangedOption);
            Achievements.numPixelTaps += 1;
            Statistics.clickEvent();
            oldI = newI;
            oldJ = newJ;
            return true;
        }
        return false;
    }

    private boolean isInBoundsIJ(int i, int j) {
        return i < gridSize && j < gridSize && i >= 0 && j >= 0;
    }

    /**
     * Convert from a screen coordinate to userChoice coordinates.
     *
     * @param x x-coordinate of location.
     * @param y y-coordinate of location.
     * @return An array containing the userCoordinate i and j indices.
     */
    private int[] convertCoordToIJ(int x, int y) {
        int[] newIJ = new int[2];
        newIJ[0] = (x - startX) / width;
        newIJ[1] = (y - startY) / width;
        return newIJ;
    }

    private void switchPixel(int i, int j, PixelOptions option) {
        userChoices[i][j] = option;
    }

    private PixelOptions switchPixel(int i, int j) {
        switch (userChoices[i][j]) {
            case EMPTY: //empty, change to color
                userChoices[i][j] = PixelOptions.COLOUR;
                break;
            case COLOUR: //has color, change to X
                userChoices[i][j] = PixelOptions.X;
                break;
            case X: //has an X, change to empty
                userChoices[i][j] = PixelOptions.EMPTY;
                break;
            default:
                break;
        }
        return userChoices[i][j];
    }
}
