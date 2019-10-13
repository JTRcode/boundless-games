package com.example.boundless.PixelGame;

/**
 * A manager for pixels.
 */
public class PixelManager {
    /**
     *
     * @param x the x coordinate of the pixel that the user what to change
     * @param y the y coordinate of the pixel that the user what to change
     * @param color the color filled the pixel and it is greater than 1
     */
    public void switchPixel(int x, int y, int color) {
        //TODO
        if(PixelGame.userChoice[x][y] == 0){ // empty
            PixelGame.userChoice[x][y] = color;  // it is filled with the color
        }
        else if (PixelGame.userChoice[x][y] > 1){ // it is filled with color
            PixelGame.userChoice[x][y] = 1; // it is an X
        }
        else{ // it is an X
            PixelGame.userChoice[x][y] = 0; // back to empty
        }
    }

    /**
     * Labels the top and side with the correct numbers.
     */
    public void label() {
        //TODO
    }

    /**
     * Draw the grid with the pixels, and the labels on the row/column.
     */
    public void draw() {
        //TODO

    }
}
