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

    /**
     * array of the color;
     */
    int[] colors = new int[3];

    public PixelManager() {
    }

    public PixelManager(int size) {
        gridSize = size;
    }

    /**
     * add all the levels into the level
     */
    public void addAll(){
        addLevel(HardCodeEasy());
        addLevel(HardCodeMed());
        addLevel(HardCodeHard());
    }

    /**
     * Add a level to this game
     *
     * @param levelToAdd the array containing the pixels of the new level.
     */
    public void addLevel(int[][] levelToAdd) {
        levels.add(levelToAdd.clone());
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
        for (int row = 0; row < gridSize; row++) labels.add(labelSet(level, row, true));
        for (int col = 0; col < gridSize; col++) labels.add(labelSet(level, col, false));
        return labels;
    }

    /**
     * The first level
     * @return the picture of the first level
     */
    private int[][] HardCodeEasy(){
        int[][] Heart = new int[gridSize][gridSize]; //The first level is a heart
        Heart[1][2] = colors[0];
        Heart[1][7] = colors[0];
        for(int i = 0; i < 3; i++){
            Heart[2][1+i] = colors[0];
            Heart[2][6+i] = colors[0];
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 10; j++){
                Heart[3+i][j] = colors[0];
            }
        }
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 8 - 2*i; j++){
                Heart[6+i][1+i+j] = colors[0];
            }
        }
        return Heart;
    }

    /**
     * The second one
     * @return the picture of second level
     */
    private int[][] HardCodeMed(){
        int[][] Bugdroid = new int[gridSize][gridSize];//the second level is Bugdroid
        //The head
        Bugdroid[0][4] = colors[1];
        Bugdroid[0][5] = colors[1];
        //The body and the arm
        for(int i = 0; i < 4; i++){
            Bugdroid[1][3+i] = colors[1];
        }
        for(int i = 0; i < 3; i++){
            Bugdroid[4+i][0] = colors[1];
            Bugdroid[4+i][9] = colors[1];
        }
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                Bugdroid[2+i][2+j] = colors[1];
            }
        }
        Bugdroid[3][1] = colors[1];
        Bugdroid[3][8] = colors[1];
        Bugdroid[2][3] = 0;
        Bugdroid[2][6] = 0;

        //The leg
        Bugdroid[8][3] = colors[1];
        Bugdroid[8][6] = colors[1];
        return Bugdroid;
    }

    /**
     * The hardest level
     * @return the picture of the hardest level
     */
    private int[][] HardCodeHard(){
        int[][] Taiji = new int[gridSize][gridSize]; // The third one is Taiji
        for(int i = 0; i < 4; i++){
            Taiji[0][3+i] = colors[2];
            Taiji[9][3+i] = colors[2];
            Taiji[3+i][0] = colors[2];
            Taiji[3+i][9] = colors[2];
            Taiji[5][1+i] = colors[2];
        }
        for(int i = 0; i < 6; i++){
            Taiji[1][2+i] = colors[2];
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 6; j++){
                Taiji[i+2][1+j] = colors[2];
            }
        }
        for(int i = 0; i < 2; i++){
            Taiji[6+i][1] = colors[2];
            Taiji[2+i][8] = colors[2];
        }
        Taiji[8][2] = colors[2];
        Taiji[8][7] = colors[2];
        Taiji[7][6] = colors[2];
        Taiji[7][8] = colors[2];
        Taiji[2][3] = 0;
        return Taiji;
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
            int current = setIsRow ? level[i][j] : level[j][i];
            if (current == 0 && consecutivePixels != 0) {
                streaks.add(consecutivePixels); //streak ends
                consecutivePixels = 0;
            } else if (current > 0) {
                consecutivePixels++;            //streak continues
            }
        }
        if (consecutivePixels > 0) streaks.add(consecutivePixels);
        return streaks;
    }
}
