package com.example.boundless.games.pixel_game;

import android.graphics.Color;

import com.example.boundless.Panel;
import com.example.boundless.games.game_utilities.GameResources;

import java.util.ArrayList;
import java.util.List;

/**
 * A level in the pixel game
 */
public class PixelLevel {
    private int[][] pixels;
    private int color = Color.CYAN;
    private List<List<Integer>> labels;

    /**
     * Set the pixel array for this level
     *
     * @param pixels The array of pixels
     */
    void setPixels(int[][] pixels) {
        this.pixels = pixels;
    }

    /**
     * Get the pixel array for this level
     *
     * @return The array of pixels
     */
    public int[][] getPixels() {
        return pixels;
    }

    /**
     * Set the color for this level
     *
     * @param color The color to set the level to
     */
    void setColor(int color) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        this.color = Color.argb(GameResources.ALPHA_VALUE, red, green, blue);
    }

    /**
     * Get the color for this level
     *
     * @return The color the level is set to
     */
    public int getColor() {
        return color;
    }

    /**
     * Get the width of each pixel
     *
     * @param startX The starting x location of the grid
     * @return The width of a pixel
     */
    public int getWidth(int startX) {
        return (int) (Panel.SCREEN_WIDTH - startX * 1.3) / pixels.length;
    }

    /**
     * Get the labels for this level
     *
     * @return The labels for this level
     */
    public List<List<Integer>> getLabels() {
        if (labels != null)
            return labels;
        labels = label();
        return labels;
    }

    private List<List<Integer>> label() {
        int gridSize = pixels.length;
        List<List<Integer>> labels = new ArrayList<>();
        for (int row = 0; row < gridSize; row++) labels.add(labelSet(pixels, row, true));
        for (int col = 0; col < gridSize; col++) labels.add(labelSet(pixels, col, false));
        return labels;
    }

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
}
