package com.example.boundless.PixelGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.boundless.Game;
import com.example.boundless.Panel;
import com.example.boundless.Statistics;

import java.util.List;

/**
 * A game where you use pixels to recreate an image.
 */
public class PixelGame extends Game {

    /**
     * The size of the pixel grid.
     */
    private int gridSize;

    /**
     * The current level of the game.
     */
    private int currentLevel = 0;
    /**
     * The starting coordinates of the grid on the screen.
     */
    private static final int startX = 100;
    private static final int startY = Panel.SCREEN_HEIGHT / 4;
    /**
     * The width of each grid square.
     */
    private int width;

    private Paint paint = new Paint();
    /**
     * The user's choices of pixels.
     */
    private PixelOptions[][] userChoice;

    /**
     * A manager for setting up levels and using them.
     */
    private PixelManager pixelManager;
    /**
     * The labels on the top and side of the game.
     */
    private List<List<Integer>> currentLabels;

    public PixelGame(Context context) {
        this(context, 10);
    }

    public PixelGame(Context context, int size) {
        super(context);
        gridSize = size;
        pixelManager = new PixelManager(gridSize);
        currentLabels = pixelManager.label(currentLevel);
        width = (Panel.SCREEN_WIDTH - startX * 2) / gridSize;
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
    public boolean gameOver() {
        if (!pixelManager.checkPixels(userChoice, currentLevel)) {
            return false;
        }
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
        if (coordsIJ[0] < gridSize && coordsIJ[1] < gridSize)
            switchPixel(coordsIJ[0], coordsIJ[1]);
    }

    /**
     * Draw the grid with the pixels, and the labels on the row/column.
     *
     * @param canvas The canvas to draw on
     */
    @Override
    public void draw(Canvas canvas) {
        //note: [0][gridSize - 1] is the lower left pixel
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                int filled = (userChoice[i][j] == PixelOptions.X) ? Color.DKGRAY : Color.BLACK;
                filled = (userChoice[i][j] == PixelOptions.COLOUR) ? Color.CYAN : filled;
                paint.setColor(filled);
                int[] coords = convertIJToSquare(i, j);
                canvas.drawRect(coords[0], coords[1], coords[2], coords[3], paint);
            }
        }
        drawOutlines(canvas);
        drawLabels(canvas);
    }

    /**
     * Draw the outlines of each grid square.
     *
     * @param canvas The canvas to draw on.
     */
    private void drawOutlines(Canvas canvas) {
        paint.setColor(Color.RED);
        //draw vertical lines
        for (int x = startX; x <= startX + width * gridSize; x += width) {
            if ((x - startX) % 5 == 0) paint.setStrokeWidth(4);
            else paint.setStrokeWidth(1);
            canvas.drawLine(x, startY, x, startY + width * gridSize, paint);
        }
        //draw horizontal lines
        for (int y = startY; y <= startY + width * gridSize; y += width) {
            if ((y - startY) % 5 == 0) paint.setStrokeWidth(4);
            else paint.setStrokeWidth(1);
            canvas.drawLine(startX, y, startX + width * gridSize, y, paint);
        }
    }

    /**
     * Draw the labels of each row and column.
     *
     * @param canvas The canvas to draw on.
     */
    private void drawLabels(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        int buffer = 25;
        for (int rowNum = 0; rowNum < gridSize; rowNum++) {
            List<Integer> row = currentLabels.get(rowNum);
            int drawY = startY + rowNum * width + (width / 2);
            int drawX = startX - 15 - row.size() * buffer;
            for (int entryNum = 0; entryNum < row.size(); entryNum++)
                drawString(canvas, row.get(entryNum).toString(), drawX + entryNum * buffer, drawY);
        }
        for (int colNum = gridSize; colNum < gridSize * 2; colNum++) {
            List<Integer> col = currentLabels.get(colNum);
            int drawX = startX + (colNum - gridSize) * width + (width / 2);
            int drawY = startY - col.size() * buffer;
            for (int entryNum = 0; entryNum < col.size(); entryNum++)
                drawString(canvas, col.get(entryNum).toString(), drawX, drawY + entryNum * buffer);
        }
    }

    /**
     * Draw text on the canvas.
     *
     * @param canvas The canvas to draw on.
     * @param text   The text to draw.
     * @param x      The x location to draw text at.
     * @param y      The y location to draw text at.
     */
    private void drawString(Canvas canvas, String text, int x, int y) {
        canvas.drawText(text, x, y, paint);
    }

    /**
     * Convert from userChoice ooordinates to a screen coordinate square section.
     *
     * @param i First userChoice coordinate.
     * @param j Second userChoice coordinate.
     * @return An array containing the upper left (first two) and lower left (last two) screen coordinates.
     */
    private int[] convertIJToSquare(int i, int j) {
        int[] newCoords = new int[4];
        newCoords[0] = startX + width * i;
        newCoords[1] = startY + width * j;
        newCoords[2] = startX + width * (i + 1);
        newCoords[3] = startY + width * (j + 1);
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
        newIJ[0] = (x - startX) / width;
        newIJ[1] = (y - startY) / width;
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
