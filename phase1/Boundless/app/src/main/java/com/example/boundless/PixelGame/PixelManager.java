package com.example.boundless.PixelGame;

import java.util.ArrayList;
import java.util.List;

/**
 * A manager for pixels.
 */
public class PixelManager {
    /**
     * A list of all available levels.
     */
    private List<int[][]> levels = new ArrayList<>();
    /**
     * The current level being played.
     */
    private int currentLevel = 0;
    /**
     * The size of the pixel grid.
     */
    private int gridSize = 10;

    public PixelManager() {
    }

    public PixelManager(int size) {
        gridSize = size;
    }

    /**
     * Add a level to this game
     *
     * @param levelToAdd the array containing the pixels of the new level.
     */
    public void addLevel(int[][] levelToAdd) {
        levels.add(levelToAdd);
    }

    /**
     * Checks if the users choices are correct.
     *
     * @return A boolean telling if the answer is correct.
     */
    public boolean checkPixels(PixelOptions[][] userChoices) {
        int[][] level = levels.get(currentLevel);
        for (int row = 0; row < gridSize; row++)
            for (int col = 0; col < gridSize; col++)
                if (doesNotMatch(userChoices[row][col], level[row][col]))
                    return false;
        currentLevel++;
        return true;
    }

    /**
     * Returns if the pixelOption matches the level answer for a pixel
     *
     * @param userOption  The user input of what the pixel is
     * @param levelOption The answer of what the pixel is
     * @return If the user input matches the correct answer
     */
    private boolean doesNotMatch(PixelOptions userOption, int levelOption) {
        return ((levelOption == 0 && userOption == PixelOptions.COLOUR) || (levelOption > 0 && userOption != PixelOptions.COLOUR));
    }

    /**
     * Labels the top and side with the correct numbers.
     *
     * @param level The 2D array of pixels that creates the image.
     * @return A list with the first half as rows and the second half as column labels.
     */
    public List<List<Integer>> label(int[][] level) {
        List<List<Integer>> labels = new ArrayList<>();
        for (int row = 0; row < gridSize; row++) {
            labels.add(labelSet(level, row, true));
        }
        for (int col = 0; col < gridSize; col++) {
            labels.add(labelSet(level, col, false));
        }
        return labels;
    }

    /**
     * Returns the label for a given row or column (a given set).
     *
     * @param level    The 2D array of pixels to label the set from.
     * @param i        The row or column to look at.
     * @param setIsRow Is this set a row?
     * @return The label for that row or column.
     */
    private List<Integer> labelSet(int[][] level, int i, boolean setIsRow) {
        int consecutivePixels = 0;
        List<Integer> streaks = new ArrayList<>();
        for (int j = 0; j < gridSize; j++) {
            if (setIsRow) {
                if (level[i][j] == 0 && consecutivePixels != 0) {
                    streaks.add(consecutivePixels); //streak ends
                    consecutivePixels = 0;
                } else if (level[i][j] > 0) {
                    consecutivePixels++;            //streak continues
                }
            } else {
                if (level[j][i] == 0 && consecutivePixels != 0) {
                    streaks.add(consecutivePixels); //streak ends
                    consecutivePixels = 0;
                } else if (level[j][i] > 0) {      //streak continues
                    consecutivePixels++;
                }
            }
        }
        if (consecutivePixels > 0) streaks.add(consecutivePixels);
        return streaks;
    }
}
