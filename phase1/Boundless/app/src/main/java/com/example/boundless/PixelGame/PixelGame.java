package com.example.boundless.PixelGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.boundless.Game;

/**
 * A game where you use pixels to recreate an image.
 */
public class PixelGame extends Game {
    /**
     * The size of the pixel grid.
     */
    private int gridSize = 10;
    /**
     * Whether the pixel is occupied
     */
    private int occupied = 1;


    Paint paintText = new Paint();
    /**
     * The user's choices of pixels.
     */
    private static PixelOptions[][] userChoice;

    /**
     * A manager for setting up levels and using them
     */
    private PixelManager pixelManager = new PixelManager(gridSize);

    public PixelGame() {
        paintText.setColor(Color.WHITE);
        paintText.setTextSize(36);
    }

    public PixelGame(int size) {
        gridSize = size;
        paintText.setColor(Color.WHITE);
        paintText.setTextSize(36);
    }

    @Override
    public boolean gameOver() {
        return pixelManager.checkPixels(userChoice);
    }

    /**
     * Switches the pixel at the given coordinates from empty to a colour to an X.
     *
     * @param x the x coordinate of the pixel that the user what to change
     * @param y the y coordinate of the pixel that the user what to change
     */
    public static void switchPixel(int x, int y) {
        switch (userChoice[x][y]) {
            case EMPTY: //empty, change to color
                userChoice[x][y] = PixelOptions.COLOUR;
                break;
            case COLOUR: //has color, change to X
                userChoice[x][y] = PixelOptions.X;
                break;
            case X: //has an X, change to empty
                userChoice[x][y] = PixelOptions.EMPTY;
                break;
            default:
                break;
        }
    }

    /**
     * Draw the grid with the pixels, and the labels on the row/column.
     */
    public void draw() {
        //TODO
    }

    /**
     * Hardcode the level
     * @param choice the choice of levels, currently three choices
     * @return the level
     */
    public int[][] DrawLevel(int choice){
        if(choice == 1){
            return DrawLevelEasy();
        }
        else if(choice == 2){
            return DrawLevelMed();
        }
        return DrawLevelHard();
    }

    /**
     * The first level
     * @return the picture of the first level
     */
    public int[][] DrawLevelEasy(){
        int[][] Heart = new int[gridSize][gridSize]; //The first level is a heart
        Heart[1][2] = occupied;
        Heart[1][7] = occupied;
        for(int i = 0; i < 3; i++){
            Heart[2][1+i] = occupied;
            Heart[2][6+i] = occupied;
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 10; j++){
                Heart[3+i][j] = occupied;
            }
        }
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 8 - 2*i; j++){
                Heart[6+i][1+i+j] = occupied;
            }
        }
        return Heart;
    }

    /**
     * The second one
     * @return the picture of second level
     */
    public int[][] DrawLevelMed(){
        int[][] Bugdroid = new int[gridSize][gridSize];//the second level is Bugdroid
        //The head
        Bugdroid[0][4] = occupied;
        Bugdroid[0][5] = occupied;
        //The body and the arm
        for(int i = 0; i < 4; i++){
            Bugdroid[1][3+i] = occupied;
        }
        for(int i = 0; i < 3; i++){
            Bugdroid[4+i][0] = occupied;
            Bugdroid[4+i][9] = occupied;
        }
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                Bugdroid[2+i][2+j] = occupied;
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
     * @return the picture of the hardest level
     */
    public int[][] DrawLevelHard(){
        int[][] Taiji = new int[gridSize][gridSize]; // The third one is Taiji
        for(int i = 0; i < 4; i++){
            Taiji[0][3+i] = occupied;
            Taiji[9][3+i] = occupied;
            Taiji[3+i][0] = occupied;
            Taiji[3+i][9] = occupied;
            Taiji[5][1+i] = occupied;
        }
        for(int i = 0; i < 6; i++){
            Taiji[1][2+i] = occupied;
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 6; j++){
                Taiji[i+2][1+j] = occupied;
            }
        }
        for(int i = 0; i < 2; i++){
            Taiji[6+i][1] = occupied;
            Taiji[2+i][8] = occupied;
        }
        Taiji[8][2] = occupied;
        Taiji[8][7] = occupied;
        Taiji[7][6] = occupied;
        Taiji[7][8] = occupied;
        Taiji[2][3] = 0;
        return Taiji;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawText("JACKSONNNNN", 100, 100, paintText);
    }

}
