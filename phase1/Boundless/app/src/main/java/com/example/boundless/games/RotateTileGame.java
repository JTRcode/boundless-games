package com.example.boundless.games;

import com.example.boundless.utilities.DrawUtility;
import com.example.boundless.games.rotate_tile_game.TileEnum;
import com.example.boundless.games.rotate_tile_game.TileManager;
import com.example.boundless.games.rotate_tile_game.tiles.Rotation;
import com.example.boundless.games.rotate_tile_game.tiles.Tile;
import com.example.boundless.games.rotate_tile_game.tiles.TileFactory;

/**
 * A game where you rotate tiles to get from point A to point B.
 */
public class RotateTileGame extends Game {

    private TileManager manager = new TileManager(4);
    private Tile[][] userChoice;


    public RotateTileGame() {
        Tile startPipe = TileFactory.createTile(TileEnum.I, manager.getTileSize());
        startPipe.setTile(Rotation.EAST);
        userChoice = manager.getTileStage();
    }

    @Override
    boolean gameOver() {
        //TODO don't use a try catch for phase 2
        if (manager.gameOver(userChoice)) {
            try {
                userChoice = manager.getTileStage();
            } catch (IndexOutOfBoundsException e) {
                //out of levels, throws an exception
                return true;
            }
        }
        Tile startPipe = TileFactory.createTile(TileEnum.I, manager.getTileSize());
        startPipe.setTile(Rotation.EAST);
        return false;
    }

    @Override
    public void draw() {
        Tile startPipe = TileFactory.createTile(TileEnum.I, manager.getTileSize());
        startPipe.setTile(Rotation.EAST);
        DrawUtility.drawBitmap(startPipe.rotatedImage, manager.getStartX() - manager.getTileSize(),
                manager.getStartY());
        DrawUtility.drawBitmap(startPipe.rotatedImage, manager.getStartX() +
                (manager.getGridSize()) * manager.getTileSize(),
                manager.getStartY() + (manager.getGridSize() - 1) * manager.getTileSize());
        for (int i = 0; i < userChoice.length; i++) {
            for (int j = 0; j < userChoice.length; j++) {
                DrawUtility.drawBitmap(userChoice[i][j].rotatedImage,
                        manager.getStartX() + j * manager.getTileSize(),
                        manager.getStartY() + manager.getTileSize() * i);
            }
        }
    }

    /**
     * Deal with the screen being touched, and check if the game is now over
     *
     * @param x The x coordinate of the touched location.
     * @param y The y coordinate of the touched location.
     */
    @Override
    public void screenTouched(int x, int y) {
        int i = (y - manager.getStartY()) / manager.getTileSize();
        int j = (x - manager.getStartX()) / manager.getTileSize();
        if (i < manager.getGridSize() && j < manager.getGridSize())
            userChoice[i][j].rotateTile();
    }
}
