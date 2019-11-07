package com.example.boundless.games.pixel_game;

import android.graphics.Color;

import com.example.boundless.Panel;
import com.example.boundless.utilities.DrawUtility;

import java.util.List;

/**
 * Draws the pixel game
 */
public class PixelDrawer {

    private static int START_X;
    private static int START_Y = Panel.SCREEN_HEIGHT / 4;
    private int width;
    private List<List<Integer>> currentLabels;

    /**
     * Creates a pixel drawer
     *
     * @param width         The width of each pixel
     * @param currentLabels The labels of the current level
     */
    public PixelDrawer(int width, List<List<Integer>> currentLabels, int startX) {
        START_X = startX;
        setupLevel(width, currentLabels);
    }

    /**
     * Get the starting x position
     * @return The x position to start at
     */
    static int getStartX() {
        return START_X;
    }
    /**
     * Get the starting y position
     * @return The y position to start at
     */
    static int getStartY() {
        return START_Y;
    }

    private void setupLevel(int width, List<List<Integer>> currentLabels) {
        this.width = width;
        this.currentLabels = currentLabels;
    }

    /**
     * Draw the game
     * @param userChoice The user's choices of each pixel
     */
    public void draw(PixelOptions[][] userChoice) {
        int gridSize = userChoice.length;
        //note: [0][gridSize - 1] is the lower left pixel
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                int filled = (userChoice[i][j] == PixelOptions.X) ? Color.DKGRAY : Color.BLACK;
                filled = (userChoice[i][j] == PixelOptions.COLOUR) ? Color.CYAN : filled;
                int[] coords = convertIJToSquare(i, j, width);
                DrawUtility.drawRectangle(coords, filled);
            }
        }
        drawOutlines(gridSize, width);
        drawLabels(gridSize, width);
    }

    private void drawOutlines(int gridSize, int width) {
        //draw vertical lines
        for (int x = START_X; x <= START_X + width * gridSize; x += width) {
            int strokeWidth = ((x - START_X) % 5 == 0) ? 4 : 1;
            DrawUtility.drawLines(new int[]{x, START_Y, x, START_Y + width * gridSize}, Color.RED, strokeWidth);
        }
        //draw horizontal lines
        for (int y = START_Y; y <= START_Y + width * gridSize; y += width) {
            int strokeWidth = ((y - START_Y) % 5 == 0) ? 4 : 1;
            DrawUtility.drawLines(new int[]{START_X, y, START_X + width * gridSize, y}, Color.RED, strokeWidth);
        }
    }

    private void drawLabels(int gridSize, int width) {
        int buffer = 25;
        for (int rowNum = 0; rowNum < gridSize; rowNum++) {
            List<Integer> row = currentLabels.get(rowNum);
            int drawY = START_Y + rowNum * width + (width / 2);
            int drawX = START_X - 15 - row.size() * buffer;
            for (int entryNum = 0; entryNum < row.size(); entryNum++)
                DrawUtility.drawString(row.get(entryNum).toString(), drawX + entryNum * buffer, drawY, Color.WHITE, 30);
        }
        for (int colNum = gridSize; colNum < gridSize * 2; colNum++) {
            List<Integer> col = currentLabels.get(colNum);
            int drawX = START_X + (colNum - gridSize) * width + (width / 2);
            int drawY = START_Y - col.size() * buffer;
            for (int entryNum = 0; entryNum < col.size(); entryNum++)
                DrawUtility.drawString(col.get(entryNum).toString(), drawX, drawY + entryNum * buffer, Color.WHITE, 30);
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
        newCoords[0] = START_X + width * i;
        newCoords[1] = START_Y + width * j;
        newCoords[2] = START_X + width * (i + 1);
        newCoords[3] = START_Y + width * (j + 1);
        return newCoords;
    }
}
