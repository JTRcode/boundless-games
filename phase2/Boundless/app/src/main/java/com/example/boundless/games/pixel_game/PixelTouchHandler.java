package com.example.boundless.games.pixel_game;

import android.view.MotionEvent;

import com.example.boundless.games.game_utilities.*;

import static com.example.boundless.games.pixel_game.PixelOptions.*;

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
    private PixelOptions[][] userChoices;

    /**
     * Create a new touch handler for the pixel game
     *
     * @param drawer The drawer that draws the game
     */
    public PixelTouchHandler(IGridDrawer<PixelOptions> drawer) {
        width = drawer.getWidth();
        userChoices = drawer.getUserChoices();
        this.gridSize = userChoices.length;
    }

    /**
     * Deal with the screen being touched
     *
     * @param event The event that occurred on the screen
     */
    public void screenTouched(MotionEvent event) {
        if (ifCancelEvent(event)) return;

        int[] oldCoords = convertCoordToIJ((int) event.getHistoricalX(0), (int) event.getHistoricalY(0));
        if (!changeIfCoords(oldCoords)) newI = newJ = -1;

        for (int pos = 0; pos < event.getHistorySize(); pos++) {
            int[] coordsIJ = convertCoordToIJ((int) event.getHistoricalX(pos), (int) event.getHistoricalY(pos));
            changeIfCoords(coordsIJ);
        }
    }

    private boolean ifCancelEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            oldI = -1;
            return true;
        }
        return (event.getHistorySize() < 1);
    }

    private boolean changeIfCoords(int[] coordsIJ) {
        newI = coordsIJ[0];
        newJ = coordsIJ[1];
        if ((newI != oldI || newJ != oldJ) && isInBoundsIJ(newI, newJ)) {
            switchPixel(newI, newJ);
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

    /**
     * Switches the pixel at the given coordinates from empty to a colour to an X.
     *
     * @param i the i coordinate of the pixel that the user what to change
     * @param j the j coordinate of the pixel that the user what to change
     */
    private void switchPixel(int i, int j) {
        switch (userChoices[i][j]) {
            case EMPTY: //empty, change to color
                userChoices[i][j] = PixelOptions.COLOUR;
                break;
            case COLOUR: //has color, change to X
                userChoices[i][j] = PixelOptions.X;
                break;
            case X: //has an X, change to empty
                userChoices[i][j] = EMPTY;
                break;
            default:
                break;
        }
    }
}
