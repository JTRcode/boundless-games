package com.example.boundless.PixelGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.boundless.Game;
import com.example.boundless.Panel;

/**
 * A game where you use pixels to recreate an image.
 */
public class PixelGame extends Game {
    /**
     * The size of the pixel grid.
     */
    private int gridSize;
    /**
     * Whether the pixel is occupied
     */
    private int occupied = 1;

    private final int sX = 100;
    private final int sY = 100;
    private int w;
    private final int buf = 2;
    private float[] gameOutline;
    /**
     * The user's choices of pixels.
     */
    private PixelOptions[][] userChoice;

    /**
     * A manager for setting up levels and using them
     */
    private PixelManager pixelManager = new PixelManager(gridSize);

    public PixelGame() {
        this(10);
    }

    public PixelGame(int size) {
        gridSize = size;
        w = (Panel.screenWidth - sX * 2) / gridSize;
        gameOutline = new float[]{sX - buf, sY - buf, sX + w * gridSize + buf, sY - buf,
                sX + w * gridSize + buf, sY - buf, sX + w * gridSize + buf, sY + w * gridSize + buf,
                sX + w * gridSize + buf, sY + w * gridSize + buf, sX - buf, sY + w * gridSize + buf,
                sX - buf, sY + w * gridSize + buf, sX - buf, sY - buf};
        userChoice = new PixelOptions[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++)
                userChoice[i][j] = PixelOptions.EMPTY;
    }

    @Override
    public boolean gameOver() {
        return pixelManager.checkPixels(userChoice);
    }

    /**
     * Deal with the screen being touched.
     * @param x The x coordinate of the touched location.
     * @param y The y coordinate of the touched location.
     */
    public void screenTouched(int x, int y) {
        int[] coordsIJ = convertCoordToIJ(x, y);
        System.out.printf("POINTER CALLED SCREEN TOUCHED!(%d, %d) converted to (%d, %d)%n", x, y, coordsIJ[0], coordsIJ[1]);
        if (coordsIJ[0] >= gridSize || coordsIJ[1] >= gridSize)
            return; // user touched outside of playing area
        switchPixel(coordsIJ[0], coordsIJ[1]);
    }


    /**
     * Draw the grid with the pixels, and the labels on the row/column.
     * @param canvas The canvas to draw on
     */
    @Override
    public void draw(Canvas canvas) {
        //note: [0][gridSize - 1] is the lower left pixel
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);
        canvas.drawLines(gameOutline, paint);
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                int filled = (userChoice[i][j] == PixelOptions.X) ? Color.LTGRAY : Color.BLACK;
                filled = (userChoice[i][j] == PixelOptions.COLOUR) ? Color.CYAN : filled;
                paint.setColor(filled);
                int[] coords = convertIJToSquare(i, j);
                canvas.drawRect(coords[0], coords[1], coords[2], coords[3], paint);
            }
        }
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
        newCoords[0] = sX + w * i;
        newCoords[1] = sY + w * j;
        newCoords[2] = sX + w * (i + 1);
        newCoords[3] = sY + w * (j + 1);
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
        newIJ[0] = (x - sX) / w;
        newIJ[1] = (y - sY) / w;
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

    /**
     * Hardcode the level
     *
     * @param choice the choice of levels, currently three choices
     * @return the level
     */
    public int[][] DrawLevel(int choice) {
        if (choice == 1) {
            return DrawLevelEasy();
        } else if (choice == 2) {
            return DrawLevelMed();
        }
        return DrawLevelHard();
    }//TODO: @jenna all the level setup should happen in the manager, these methods can also be private

    /**
     * The first level
     *
     * @return the picture of the first level
     */
    public int[][] DrawLevelEasy() {
        int[][] Heart = new int[gridSize][gridSize]; //The first level is a heart
        Heart[1][2] = occupied;
        Heart[1][7] = occupied;
        for (int i = 0; i < 3; i++) {
            Heart[2][1 + i] = occupied;
            Heart[2][6 + i] = occupied;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                Heart[3 + i][j] = occupied;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8 - 2 * i; j++) {
                Heart[6 + i][1 + i + j] = occupied;
            }
        }
        return Heart;
    }

    /**
     * The second one
     *
     * @return the picture of second level
     */
    public int[][] DrawLevelMed() {
        int[][] Bugdroid = new int[gridSize][gridSize];//the second level is Bugdroid
        //The head
        Bugdroid[0][4] = occupied;
        Bugdroid[0][5] = occupied;
        //The body and the arm
        for (int i = 0; i < 4; i++) {
            Bugdroid[1][3 + i] = occupied;
        }
        for (int i = 0; i < 3; i++) {
            Bugdroid[4 + i][0] = occupied;
            Bugdroid[4 + i][9] = occupied;
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                Bugdroid[2 + i][2 + j] = occupied;
            }
        }
        Bugdroid[3][1] = occupied;
        Bugdroid[3][8] = occupied;
        Bugdroid[2][3] = 0;
        Bugdroid[2][6] = 0;

        //The leg
        Bugdroid[8][3] = occupied;
        Bugdroid[8][6] = occupied;
        return Bugdroid;
    }

    /**
     * The hardest level
     *
     * @return the picture of the hardest level
     */
    public int[][] DrawLevelHard() {
        int[][] Taiji = new int[gridSize][gridSize]; // The third one is Taiji
        for (int i = 0; i < 4; i++) {
            Taiji[0][3 + i] = occupied;
            Taiji[9][3 + i] = occupied;
            Taiji[3 + i][0] = occupied;
            Taiji[3 + i][9] = occupied;
            Taiji[5][1 + i] = occupied;
        }
        for (int i = 0; i < 6; i++) {
            Taiji[1][2 + i] = occupied;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                Taiji[i + 2][1 + j] = occupied;
            }
        }
        for (int i = 0; i < 2; i++) {
            Taiji[6 + i][1] = occupied;
            Taiji[2 + i][8] = occupied;
        }
        Taiji[8][2] = occupied;
        Taiji[8][7] = occupied;
        Taiji[7][6] = occupied;
        Taiji[7][8] = occupied;
        Taiji[2][3] = 0;
        return Taiji;
    }

}
