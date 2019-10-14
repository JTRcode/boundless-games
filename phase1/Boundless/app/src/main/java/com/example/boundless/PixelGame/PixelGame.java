package com.example.boundless.PixelGame;

import com.example.boundless.Game;

/**
 * A game where you use pixels to recreate an image.
 */
public class PixelGame extends Game {
    /**
     * The size of the pixel grid.
     */
    private int gridSize = 10;

    /**
     * The user's choices of pixels.
     */
    private static PixelOptions[][] userChoice;

    /**
     * A manager for setting up levels and using them
     */
    private PixelManager pixelManager = new PixelManager(gridSize);

    public PixelGame(){}

    public PixelGame(int size) {
        gridSize = size;
    }

    /**
     * Switches the pixel at the given coordinates from empty to a colour to an X.
     *
     * @param x the x coordinate of the pixel that the user what to change
     * @param y the y coordinate of the pixel that the user what to change
     */
    public static void switchPixel(int x, int y) {
        switch (PixelGame.userChoice[x][y]) {
            case EMPTY: //empty, change to color
                PixelGame.userChoice[x][y] = PixelOptions.COLOUR;
                break;
            case COLOUR: //has color, change to X
                PixelGame.userChoice[x][y] = PixelOptions.X;
                break;
            case X: //has an X, change to empty
                PixelGame.userChoice[x][y] = PixelOptions.EMPTY;
                break;
            default:
                break;
        }
    }

    /**
     * Draw the grid with the pixels, and the labels on the row/column.
     */
    public void draw() {
        //TODO
    }
}
