package com.example.boundless.games.game_utilities;

import com.example.boundless.Panel;

/**
 * Holds the resources for each game, e.g. game over strings and magic constants
 */
public final class GameResources {
    private GameResources() {
    }

    /**
     * The x start position for the pixel grid on the screen
     */
    public static final int PIXEL_START_X = 150;
    /**
     * The y start position for the pixel grid on the screen
     */
    public static final int PIXEL_START_Y = Panel.SCREEN_HEIGHT / 4;

    /**
     * Gets the game over text for the pixel game
     *
     * @param finishedLevel The level the user has finished
     * @return The string to show to the user.
     */
    public static String getPixelGameOver(int finishedLevel) {
        return "GAME OVER!\nYou just finished level " + finishedLevel;
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
}
