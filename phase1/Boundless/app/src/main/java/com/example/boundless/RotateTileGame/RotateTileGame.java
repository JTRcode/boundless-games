package com.example.boundless.RotateTileGame;

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

    //TODO
}
