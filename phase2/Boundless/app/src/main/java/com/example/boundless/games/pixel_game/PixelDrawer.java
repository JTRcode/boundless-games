package com.example.boundless.games.pixel_game;

import android.graphics.Color;

import com.example.boundless.games.game_utilities.*;
import com.example.boundless.utilities.DrawUtility;
import com.example.boundless.utilities.HandleCustomization;

import java.util.List;

/**
 * Draws the pixel game with labels on the top and side
 */
public class PixelDrawer implements IGridDrawer {

    private int startX = GameResources.PIXEL_START_X;
    private int startY = GameResources.PIXEL_START_Y;
    private int levelColor;
    private int width;
    private int gridSize;
    private PixelLevel level;
    private List<List<Integer>> currentLabels;
    private PixelOptions[][] userChoices;
    private static int hint = 0;

    /**
     * Creates a pixel drawer
     */
    public PixelDrawer(IGridManager<PixelOptions, PixelLevel> manager) {
        level = manager.getLevel();
        gridSize = manager.getGridSize();
        userChoices = manager.getUserChoices();

        levelColor = level.getColor();
        currentLabels = level.getLabels();
        width = level.getWidth(startX);

        setupLevel(width, currentLabels);
        emptyUserChoices();
    }

    private void emptyUserChoices() {
        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++)
                userChoices[i][j] = PixelOptions.EMPTY;
    }

    /**
     * Get the starting x position
     *
     * @return The x position to start at
     */
    public int getStartX() {
        return startX;
    }

    /**
     * Get the starting y position
     *
     * @return The y position to start at
     */
    public int getStartY() {
        return startY;
    }

    private void setupLevel(int width, List<List<Integer>> currentLabels) {
        this.width = width;
        this.currentLabels = currentLabels;
    }

    /**
     * User needs a hint
     */
    public static void showHint() {
        hint += GameResources.PIXEL_HINT_FRAMES;
    }

    /**
     * Draw the game
     */
    @Override
    public void draw() {
        if (hint > 0) {
            drawHint();
            hint--;
            return;
        }
        //note: [0][gridSize - 1] is the lower left pixel
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                int filled = (userChoices[i][j] == PixelOptions.X) ? GameResources.PIXEL_X_COLOR : GameResources.PIXEL_EMPTY_COLOR;
                filled = (userChoices[i][j] == PixelOptions.COLOUR) ? levelColor : filled;
                int[] coords = convertIJToSquare(i, j, width);
                DrawUtility.drawRectangle(coords, filled);
            }
        }
        drawOutlines(gridSize, width);
        drawLabels(gridSize, width);
    }

    private void drawHint() {
        int[][] answer = level.getPixels();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                int filled = (answer[j][i] > 0) ? levelColor : GameResources.PIXEL_EMPTY_COLOR;
                int[] coords = convertIJToSquare(i, j, width);
                DrawUtility.drawRectangle(coords, filled);
            }
        }
    }

    private void drawOutlines(int gridSize, int width) {
        //draw vertical lines
        for (int x = startX; x <= startX + width * gridSize; x += width) {
            int strokeWidth = ((x - startX) % 5 == 0) ? 4 : 1;
            DrawUtility.drawLines(new int[]{x, startY, x, startY + width * gridSize}, Color.RED, strokeWidth);
        }
        //draw horizontal lines
        for (int y = startY; y <= startY + width * gridSize; y += width) {
            int strokeWidth = ((y - startY) % 5 == 0) ? 4 : 1;
            DrawUtility.drawLines(new int[]{startX, y, startX + width * gridSize, y}, Color.RED, strokeWidth);
        }
    }

    private void drawLabels(int gridSize, int width) {
        int color = HandleCustomization.getPixelLabelColor();
        int buffer = 25;
        for (int rowNum = 0; rowNum < gridSize; rowNum++) {
            List<Integer> row = currentLabels.get(rowNum);
            int drawY = startY + rowNum * width + (width / 2);
            int drawX = startX - 15 - row.size() * buffer;
            for (int entryNum = 0; entryNum < row.size(); entryNum++)
                DrawUtility.drawString(row.get(entryNum).toString(), drawX + entryNum * buffer, drawY, color, 30);
        }
        for (int colNum = gridSize; colNum < gridSize * 2; colNum++) {
            List<Integer> col = currentLabels.get(colNum);
            int drawX = startX + (colNum - gridSize) * width + (width / 2);
            int drawY = startY - col.size() * buffer;
            for (int entryNum = 0; entryNum < col.size(); entryNum++)
                DrawUtility.drawString(col.get(entryNum).toString(), drawX, drawY + entryNum * buffer, color, 30);
        }
    }

    /**
     * Convert from userChoice coordinates to a screen coordinate square section.
     *
     * @param i First userChoice coordinate.
     * @param j Second userChoice coordinate.
     * @return An array containing the upper left (first two) and lower left (last two) screen coordinates.
     */
    private int[] convertIJToSquare(int i, int j, int width) {
        int[] newCoords = new int[4];
        newCoords[0] = startX + width * i;
        newCoords[1] = startY + width * j;
        newCoords[2] = startX + width * (i + 1);
        newCoords[3] = startY + width * (j + 1);
        return newCoords;
    }
}
