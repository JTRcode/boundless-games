package com.example.boundless.games.game_utilities;

import android.graphics.Color;

import com.example.boundless.Panel;

/**
 * Holds the resources for each game, e.g. "game over" strings and magic constants
 */
public final class GameResources {
    private GameResources() {
    }

    //region Pixel game
    /**
     * The alpha value of the pixels
     */
    public static final int ALPHA_VALUE = 200;
    /**
     * The x start position for the pixel grid on the screen
     */
    public static final int PIXEL_START_X = 150;
    /**
     * The y start position for the pixel grid on the screen
     */
    public static final int PIXEL_START_Y = Panel.SCREEN_HEIGHT / 4;
    /**
     * The color of the unfilled pixels in the pixel game
     */
    public static final int PIXEL_EMPTY_COLOR = Color.argb(ALPHA_VALUE, 0, 0, 0);
    /**
     * The color of the X pixels in the  pixel game
     */
    public static final int PIXEL_X_COLOR = Color.argb(ALPHA_VALUE, 143, 143, 143);
    /**
     * The number of frames to show the pixel hint for
     */
    public static final int PIXEL_HINT_FRAMES = 8;
    /**
     * Gets the game over text for the pixel game
     *
     * @param finishedLevel The level the user has finished
     * @return The string to show to the user.
     */
    public static String getPixelGameOver(int finishedLevel) {
        return "GAME COMPLETED!\nYou just finished level " + finishedLevel;
    }

    /**
     * Gets the instructions text for the pixel game
     *
     * @return The string to show the user.
     */
    public static String getPixelInstructions() {
        return "Use the column and row numbers as a guide to create a picture:\n" +
                "each row and column has a number of groupings of pixels (e.g. 10 means 10 pixels in a row in that row or column)";
    }
    //endregion

    //region Tile game

    //constants for the tile game go here

    //endregion
    //region GPA catcher game

    //constants for the gpa catcher game go here

    //endregion
}
