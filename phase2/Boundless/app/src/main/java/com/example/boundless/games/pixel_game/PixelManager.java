package com.example.boundless.games.pixel_game;

import com.example.boundless.games.game_utilities.IGridManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A manager for pixels.
 */
public class PixelManager implements IGridManager<PixelOptions> {
    private static List<int[][]> levels = new ArrayList<>();
    private int[][] currentLevel;
    private PixelOptions[][] userChoices;

    public PixelManager(int level) {
        setupLevels();
        currentLevel = levels.get(level);
        userChoices = new PixelOptions[getGridSize()][getGridSize()];
    }

    public PixelOptions[][] getUserChoices() {
        return userChoices;
    }

    /**
     * Get the grid size of the current level
     *
     * @return The grid size
     */
    public int getGridSize() {
        return currentLevel.length;
    }

    /**
     * add all the levels into the level
     */
    private void setupLevels() {
        addLevel(hardCodeLevel1());
        addLevel(hardCodeLevel2());
        addLevel(hardCodeLevel3());
        addLevel(hardCodeLevel4());
    }

    /**
     * Add a level to this game
     *
     * @param levelToAdd the array containing the pixels of the new level.
     */
    private void addLevel(int[][] levelToAdd) {
        levels.add(levelToAdd.clone());
    }

    /**
     * Checks if the users choices are correct.
     *
     * @return A boolean telling if the answer is correct.
     */
    public boolean checkAnswer() {
        int gridSize = currentLevel.length;
        for (int row = 0; row < gridSize; row++)
            for (int col = 0; col < gridSize; col++)
                if (doesNotMatch(userChoices[row][col], currentLevel[col][row]))
                    //TODO: should be level[row][col], not sure whats wrong
                    return false;
        return true;
    }

    private boolean doesNotMatch(PixelOptions userOption, int levelOption) {
        return ((levelOption == 0 && userOption == PixelOptions.COLOUR) || (levelOption != 0 && userOption != PixelOptions.COLOUR));
    }

    /**
     * Labels the top and side with the correct numbers.
     *
     * @return A list with the first half as rows and the second half as column labels.
     */
    public List<List<Integer>> label() {
        int gridSize = currentLevel.length;
        List<List<Integer>> labels = new ArrayList<>();
        for (int row = 0; row < gridSize; row++) labels.add(labelSet(currentLevel, row, true));
        for (int col = 0; col < gridSize; col++) labels.add(labelSet(currentLevel, col, false));
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
        int gridSize = level.length;
        List<Integer> streaks = new ArrayList<>();
        for (int j = 0; j < gridSize; j++) {
            int current = setIsRow ? level[i][j] : level[j][i];
            if (current == 0 && consecutivePixels != 0) {
                streaks.add(consecutivePixels); //streak ends
                consecutivePixels = 0;
            } else if (current != 0) {
                consecutivePixels++;            //streak continues
            }
        }
        if (consecutivePixels > 0) streaks.add(consecutivePixels);
        return streaks;
    }

    //-----------------------------------
    //Hardcode levels
    //TODO: move these hardcoded patterns to a new class

    /**
     * The first level
     *
     * @return the picture of the first level
     */
    private int[][] hardCodeLevel1() {
        int[][] heart = new int[10][10]; //The first level is a heart
        heart[1][2] = 1;
        heart[1][7] = 1;
        for (int i = 0; i < 3; i++) {
            heart[2][1 + i] = 1;
            heart[2][6 + i] = 1;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                heart[3 + i][j] = 1;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8 - 2 * i; j++) {
                heart[6 + i][1 + i + j] = 1;
            }
        }
        return heart;
    }

    /**
     * The second one
     *
     * @return the picture of second level
     */
    private int[][] hardCodeLevel2() {
        int[][] bugdroid = new int[10][10];//the second level is bugdroid
        //The head
        bugdroid[0][4] = 1;
        bugdroid[0][5] = 1;
        //The body and the arm
        for (int i = 0; i < 4; i++) {
            bugdroid[1][3 + i] = 1;
        }
        for (int i = 0; i < 3; i++) {
            bugdroid[4 + i][0] = 1;
            bugdroid[4 + i][9] = 1;
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                bugdroid[2 + i][2 + j] = 1;
            }
        }
        bugdroid[3][1] = 1;
        bugdroid[3][8] = 1;
        bugdroid[2][3] = 0;
        bugdroid[2][6] = 0;

        //The leg
        bugdroid[8][3] = 1;
        bugdroid[8][6] = 1;
        return bugdroid;
    }

    private int[][] hardCodeLevel3() {

        int[][] panda = new int[10][10]; // the third one is panda
        for (int i = 0; i < 2; i++) {
            panda[0][i] = 1;
            panda[1][i] = 1;
            panda[0][8 + i] = 1;
            panda[1][8 + i] = 1;
            panda[4][2 + i] = 1;
            panda[4][6 + i] = 1;
            panda[5][1 + i] = 1;
            panda[5][4 + i] = 1;
            panda[5][7 + i] = 1;
            panda[6][2 + i] = 1;
            panda[6][6 + i] = 1;
            panda[7][4 + i] = 1;
        }
        panda[1][2] = 1;
        panda[9][2] = 1;
        for (int j = 0; j < 5; j++) {
            panda[1][3 + j] = 1;
            panda[9][3 + j] = 1;
            panda[3 + j][0] = 1;
            panda[3 + j][9] = 1;
        }
        panda[2][1] = 1;
        panda[2][8] = 1;
        panda[8][1] = 1;
        panda[8][8] = 1;
        return panda;
    }

    /**
     * The hardest level
     *
     * @return the picture of the hardest level
     */
    private int[][] hardCodeLevel4() {
        int[][] taiji = new int[10][10]; // The third one is taiji
        for (int i = 0; i < 4; i++) {
            taiji[0][3 + i] = 1;
            taiji[9][3 + i] = 1;
            taiji[3 + i][0] = 1;
            taiji[3 + i][9] = 1;
            taiji[5][1 + i] = 1;
        }
        for (int i = 0; i < 6; i++) {
            taiji[1][2 + i] = 1;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                taiji[i + 2][1 + j] = 1;
            }
        }
        for (int i = 0; i < 2; i++) {
            taiji[6 + i][1] = 1;
            taiji[2 + i][8] = 1;
        }
        taiji[8][2] = 1;
        taiji[8][7] = 1;
        taiji[7][6] = 1;
        taiji[7][8] = 1;
        taiji[2][3] = 0;
        return taiji;
    }
}
