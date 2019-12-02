package com.example.boundless.games.rotate_tile_game;

import com.example.boundless.games.game_utilities.GameResources;
import com.example.boundless.games.game_utilities.GridManager;
import com.example.boundless.games.game_utilities.IGameDrawer;
import com.example.boundless.games.rotate_tile_game.tiles.Rotation;
import com.example.boundless.games.rotate_tile_game.tiles.Tile;
import com.example.boundless.games.rotate_tile_game.tiles.TileFactory;
import com.example.boundless.utilities.DrawUtility;

/**
 * Draws the Rotate Tile Game
 */
public class RotateTileDrawer implements IGameDrawer {

    private int startX = GameResources.TILE_START_X;
    private int startY = GameResources.TILE_START_Y;
    private Tile startEndPipe;
    private int width;
    private int gridSize;
    private Tile[][] userChoices;

    /**
     * A new RotateTileDrawer
     *
     * @param manager The manager for the Rotate Tile game
     */
    public RotateTileDrawer(GridManager<Tile, TileLevel> manager) {
        width = manager.getLevel().getTileWidth(startX);
        userChoices = manager.getUserChoices();
        gridSize = manager.getGridSize();
        createStartEndTile();
    }

    private void createStartEndTile() {
        startEndPipe = TileFactory.createTile(TileEnum.I);
        startEndPipe.resize(width);
        startEndPipe.setTile(Rotation.EAST);
    }

    /**
     * Draws the Tiles to the screen
     */
    @Override
    public void draw() {
        DrawUtility.drawBitmap(startEndPipe.rotatedImage, startX - width, startY);
        DrawUtility.drawBitmap(startEndPipe.rotatedImage, startX + gridSize * width,
                startY + (gridSize - 1) * width);
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                DrawUtility.drawBitmap(userChoices[i][j].rotatedImage, startX + j * width,
                        startY + width * i);
            }
        }
    }
}
