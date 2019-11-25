package com.example.boundless.games.rotate_tile_game;

import android.view.MotionEvent;

import com.example.boundless.games.game_utilities.GameResources;
import com.example.boundless.games.game_utilities.GridManager;
import com.example.boundless.games.game_utilities.ITouchHandler;
import com.example.boundless.games.rotate_tile_game.tiles.Tile;
import com.example.boundless.stats.Achievements;
import com.example.boundless.stats.Statistics;

/**
 * Handles touch input for the rotate tile game
 */
public class RotateTileTouchHandler implements ITouchHandler {
    private int gridSize;
    private int width;
    private int startX = GameResources.TILE_START_X;
    private int startY = GameResources.TILE_START_Y;
    private Tile[][] userChoices;
    private static char hintTile = Character.MIN_VALUE;

    /**
     * Creates a RotateTileTouchHandler
     *
     * @param manager The manager for the tile game
     */
    public RotateTileTouchHandler(GridManager<Tile, TileLevel> manager) {
        width = manager.getLevel().getTileWidth(startX);
        userChoices = manager.getUserChoices();
        gridSize = manager.getGridSize();
    }

    @Override
    public void screenTouched(MotionEvent event) {
        final int pointerCount = event.getPointerCount();
        for (int pointer = 0; pointer < pointerCount; pointer++) {
            MotionEvent.PointerCoords coords = new MotionEvent.PointerCoords();
            event.getPointerCoords(pointer, coords);
            screenTouched((int) coords.x, (int) coords.y);
        }
    }

    private void screenTouched(int x, int y) {
        int i = (y - this.startY) / width;
        int j = (x - this.startX) / width;
        if (i < gridSize && j < gridSize && i >= 0 && j >= 0) {
            userChoices[i][j].rotateTile();
            Statistics.clickEvent();
            Achievements.numRotateTaps++;
        }
    }

    /**
     * Allows the user to place a given tile anywhere on the grid
     *
     * @param tile The tile the user can place
     */
    public static void setFreeTile(char tile) {
        hintTile = tile;
    }
}