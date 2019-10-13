package com.example.boundless.PixelGame;

import java.util.List;

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
    public void label(int[][] level) {
        List<List<Integer>> labels = null;
        for (int i = 0; i < PixelGame.gridSize; i++){
            //get row
            int pixelsInARow = 0;
            List<Integer> streaks = null;
            for (int j = 0; j < PixelGame.gridSize; j++){
                if (level[i][j] == 0){
                    if (pixelsInARow != 0) streaks.add(pixelsInARow);
                    pixelsInARow = 0;
                    //end streak, add streak to labels
                } else {
                    pixelsInARow++;
                    //add to streak, nothing to labels
                }
            }
            if (pixelsInARow > 0) streaks.add(pixelsInARow);
            labels.add(streaks);
            //if streak is > 0, add to labels
        }
        //TODO
    }

    /**
     * Draw the grid with the pixels, and the labels on the row/column.
     */
    public void draw() {
        //TODO

    }
}
