package com.example.boundless.games.pixel_game;

import com.example.boundless.games.GamesEnum;
import com.example.boundless.games.game_utilities.GridManager;
import com.example.boundless.stats.Statistics;

/**
 * A manager for pixels.
 */
public class PixelManager extends GridManager<PixelOptions, PixelLevel> {
    private PixelLevel currentLevel;
    private int[][] levelPixels;
    private PixelOptions[][] userChoices;

    /**
     * Creates a new PixelManager
     *
     * @param level The level to manage
     */
    public PixelManager(int level) {
        currentLevel = HardCodePixelSetups.getLevel(level);
        levelPixels = currentLevel.getPixels();
        userChoices = new PixelOptions[getGridSize()][getGridSize()];
    }

    /**
     * Gets the choices the user has picked so far
     *
     * @return The user's choices
     */
    @Override
    public PixelOptions[][] getUserChoices() {
        return userChoices;
    }

    /**
     * Gets the current level of the pixel game
     *
     * @return The current level
     */
    @Override
    public PixelLevel getLevel() {
        return currentLevel;
    }

    /**
     * Get the grid size of the current level
     *
     * @return The grid size
     */
    @Override
    public int getGridSize() {
        return levelPixels.length;
    }

    /**
     * Checks if the users choices are correct.
     *
     * @return A boolean telling if the answer is correct.
     */
    @Override
    public boolean checkAnswer() {
        int gridSize = getGridSize();
        for (int row = 0; row < gridSize; row++)
            for (int col = 0; col < gridSize; col++)
                if (doesNotMatch(userChoices[row][col], levelPixels[col][row]))
                    return false;
        Statistics.endTimeByGame(GamesEnum.PIXELS);
        return true;
    }

    private boolean doesNotMatch(PixelOptions userOption, int levelOption) {
        return ((levelOption == 0 && userOption == PixelOptions.COLOUR) || (levelOption != 0 && userOption != PixelOptions.COLOUR));
    }
}
