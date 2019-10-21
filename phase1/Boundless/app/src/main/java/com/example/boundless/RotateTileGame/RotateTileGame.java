package com.example.boundless.RotateTileGame;

import android.graphics.Canvas;

import com.example.boundless.Game;

/**
 * A game where you rotate tiles to get from point A to point B.
 */
public class RotateTileGame extends Game {

    private TileManager manager = new TileManager();
    private Tile[][] userChoice;

    @Override
    public boolean gameOver() {
        return manager.gameOver(userChoice);
        //TODO
    }


    /**
     * Rotates a given tile clockwise.
     */
    public void rotate(int x, int y) {
        userChoice[x][y].rotateTile();
    }

    @Override
    public void draw(Canvas canvas) {
        //drawBitmap(Bitmap bitmap, float left, float top, Paint paint)
        //TODO
    }
    /**
     * Deal with the screen being touched, and check if the game is now over
     *
     * @param x The x coordinate of the touched location.
     * @param y The y coordinate of the touched location.
     * @return whether the game should be over.
     */
    @Override
    public void screenTouched(int x, int y) {
        //TODO
    }

    //TODO
}
