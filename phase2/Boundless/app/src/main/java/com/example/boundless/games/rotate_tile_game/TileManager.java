package com.example.boundless.games.rotate_tile_game;

import com.example.boundless.games.game_utilities.IGridManager;
import com.example.boundless.games.rotate_tile_game.tiles.*;

/**
 * Sets up tiles and updates them
 */
public class TileManager implements IGridManager<Tile, TileLevel> {
    private TileLevel currentLevel;
    private Tile[][] userChoices;

    /**
     * Creates a new tile manager
     *
     * @param levelToPlay The level to manage
     */
    public TileManager(int levelToPlay) {
        currentLevel = HardCodeSetUps.getLevel(levelToPlay);
        userChoices = currentLevel.getTiles();
        randomizeTiles(userChoices);
    }

    @Override
    public Tile[][] getUserChoices() {
        return userChoices;
    }

    @Override
    public TileLevel getLevel() {
        return currentLevel;
    }

    @Override
    public int getGridSize() {
        return currentLevel.getGridSize();
    }

    private void randomizeTiles(Tile[][] level) {
        int gridSize = getGridSize();
        for (int row = 0; row < gridSize; row++)
            for (int col = 0; col < gridSize; col++)
                level[row][col].setTile(Rotation.getRandom());
    }

    /**
     * Checks if the user rotated the tiles to win the game.
     *
     * @return If the user has correctly completed the puzzle.
     */
    @Override
    public boolean checkAnswer() {
        int gridSize = getGridSize();
        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++)
                userChoices[i][j].visited = false;
        return gameOver(0, 0, 3, userChoices);
    }

    /**
     * Checks if the tiles are rotated to win the game.
     *
     * @param x           The x coordinate to start the path at.
     * @param y           The y coordinate to start the path at.
     * @param entrance    The number representing which side of the tile we start at.
     * @param userChoices The choices that the user picked for each tile and rotation.
     * @return If the user has correctly completed the puzzle.
     */
    private boolean gameOver(int x, int y, int entrance, Tile[][] userChoices) {
        Tile currentTile = userChoices[x][y];
        int[] exits = currentTile.getExits();
        if (exits[entrance] != 1 || currentTile.visited) return false;
        else if (atEnd(x, y, exits)) return true;

        currentTile.visited = true;
        for (int exit = 0; exit < 4; exit++) {
            if (exits[exit] == 1 && exit != entrance) {    //is an exit
                int newX = calculateNewCoord(x, exit, 0);
                int newY = calculateNewCoord(y, exit, 1);
                if (valid(newX, newY) && gameOver(newX, newY, (exit + 2) % 4, userChoices)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Calculates the next x or y coordinate based on which exit the current tile will be exiting through.
     */
    private int calculateNewCoord(int coord, int exit, int isY) {
        int toAdd = (2 - exit < 0 || (isY == 0 && 2 - exit > 0)) ? -1 : 1;
        return (exit % 2 == isY) ? coord + toAdd : coord;
    }

    /**
     * Checks if the current tile can end the game.
     */
    private boolean atEnd(int x, int y, int[] exits) {
        int gridSize = getGridSize();
        return (x == gridSize - 1 && y == gridSize - 1 && exits[1] == 1);
    }

    /**
     * Checks if the current coordinates are valid.
     */
    private boolean valid(int x, int y) {
        int gridSize = getGridSize();
        return (x < gridSize && y < gridSize && x >= 0 && y >= 0);
    }
}
