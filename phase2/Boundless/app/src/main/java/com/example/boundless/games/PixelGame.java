package com.example.boundless.games;

import android.graphics.Color;

import com.example.boundless.utilities.DrawUtility;
import com.example.boundless.Panel;
import com.example.boundless.games.pixel_game.PixelManager;
import com.example.boundless.games.pixel_game.PixelOptions;

import java.util.List;

/**
 * A game where you use pixels to recreate an image.
 */
public class PixelGame extends Game {

    private int gridSize;
    private int currentLevel;
    private static final int START_X = 100;
    private static final int START_Y = Panel.SCREEN_HEIGHT / 4;
    private int width;
    private PixelOptions[][] userChoice;
    private PixelManager pixelManager;
    private List<List<Integer>> currentLabels;

    public PixelGame() {
        this(0);
    }

    public PixelGame(int level) {
        gridSize = 10;
        currentLevel = level;
        pixelManager = new PixelManager(gridSize);
        currentLabels = pixelManager.label(currentLevel);
        width = (Panel.SCREEN_WIDTH - START_X * 2) / gridSize;
        userChoice = new PixelOptions[gridSize][gridSize];
        emptyUserChoices();
    }

    private void emptyUserChoices() {
        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++)
                userChoice[i][j] = PixelOptions.EMPTY;
    }

    /**
     * Check if the game is over
     *
     * @return If all stages have been correctly finished
     */
    @Override
    boolean gameOver() {
        if (!pixelManager.checkPixels(userChoice, currentLevel)) return false;

        currentLevel++;
        if (currentLevel < pixelManager.getNumOfLevels()) {
            currentLabels = pixelManager.label(currentLevel);
            emptyUserChoices();
            return false;
        }
        return true;
    }

    /**
     * Deal with the screen being touched.
     *
     * @param x The x coordinate of the touched location.
     * @param y The y coordinate of the touched location.
     */
    @Override
    public void screenTouched(int x, int y) {
        int[] coordsIJ = convertCoordToIJ(x, y);
        if (coordsIJ[0] < gridSize && coordsIJ[1] < gridSize && coordsIJ[0] >= 0 && coordsIJ[1] >= 0)
            switchPixel(coordsIJ[0], coordsIJ[1]);
    }

    @Override
    String getInstructions() {
        return "Use the column and row numbers as a guide to create a picture:\n" +
                "each row and column has a number of groupings of pixels (e.g. 10 means 10 pixels in a row in that row or column)";
    }

    @Override
    String getGameOverText(){
        return "GAME OVER!\nYou just finished level "+currentLevel;
    }

    /**
     * Draw the grid with the pixels, and the labels on the row/column.
     */
    @Override
    public void draw() {
        //note: [0][gridSize - 1] is the lower left pixel
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                int filled = (userChoice[i][j] == PixelOptions.X) ? Color.DKGRAY : Color.BLACK;
                filled = (userChoice[i][j] == PixelOptions.COLOUR) ? Color.CYAN : filled;
                int[] coords = convertIJToSquare(i, j);
                DrawUtility.drawRectangle(coords, filled);
            }
        }
        drawOutlines();
        drawLabels();
    }

    private void drawOutlines() {
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

    private void drawLabels() {
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
    private int[] convertIJToSquare(int i, int j) {
        int[] newCoords = new int[4];
        newCoords[0] = START_X + width * i;
        newCoords[1] = START_Y + width * j;
        newCoords[2] = START_X + width * (i + 1);
        newCoords[3] = START_Y + width * (j + 1);
        return newCoords;
    }

    /**
     * Convert from a screen coordinate to userChoice coordinates.
     *
     * @param x x-coordinate of location.
     * @param y y-coordinate of location.
     * @return An array containing the userCoordinate i and j indices.
     */
    private int[] convertCoordToIJ(int x, int y) {
        int[] newIJ = new int[2];
        newIJ[0] = (x - START_X) / width;
        newIJ[1] = (y - START_Y) / width;
        return newIJ;
    }

    /**
     * Switches the pixel at the given coordinates from empty to a colour to an X.
     *
     * @param i the i coordinate of the pixel that the user what to change
     * @param j the j coordinate of the pixel that the user what to change
     */
    private void switchPixel(int i, int j) {
        switch (userChoice[i][j]) {
            case EMPTY: //empty, change to color
                userChoice[i][j] = PixelOptions.COLOUR;
                break;
            case COLOUR: //has color, change to X
                userChoice[i][j] = PixelOptions.X;
                break;
            case X: //has an X, change to empty
                userChoice[i][j] = PixelOptions.EMPTY;
                break;
            default:
                break;
        }
    }

}
