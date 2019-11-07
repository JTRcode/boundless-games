package com.example.boundless.games.rotate_tile_game;

import android.util.Log;

import com.example.boundless.games.rotate_tile_game.tiles.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Sets up tiles and updates them
 */
public class TileManager {
    /**
     * The grid of tiles on the screen
     */
    private List<Tile[][]> levels;
    /**
     * The size of the grid
     */
    private int gridSize;

    /**
     * The current level being played.
     */
    private int currentLevel = 0;

    public TileManager() {
        levels = new ArrayList<>();
    }

    /**
     * Gets the number of levels we have
     *
     * @return The total number of levels
     */
    public int getNumOfLevels() {
        return levels.size();
    }

    public int getGridSize() {
        return gridSize;
    }

    public Tile[][] getTileStage() {
        setUpTiles();
        gridSize = (levels.get(currentLevel)).length;
        return levels.get(currentLevel);
    }

    /**
     * Initializes the tiles array and randomizes the rotation.
     */
    private void setUpTiles() {
        char[][] layout;
        switch (currentLevel) {
            case 0:
                layout = HardCodeSetUps.setUpTilesEasy();
                break;
            case 1:
                layout = HardCodeSetUps.setUpTilesMedium();
                break;
            case 2:
                layout = HardCodeSetUps.setUpTilesHard();
                break;
            case 3:
                layout = HardCodeSetUps.setUpTilesExpert();
                break;
            default:
                layout = HardCodeSetUps.setUpTilesEasy();
                Log.e(this.toString(), "current game level does not exist");
                break;
        }
        Tile[][] stage = convertCharToTile(layout);
        randomizeTiles(stage);
        addLevel(stage);
    }

    /**
     * Randomizes the tile rotation
     *
     * @param level the level to randomize the tiles of.
     */
    private void randomizeTiles(Tile[][] level) {
        for (int row = 0; row < gridSize; row++)
            for (int col = 0; col < gridSize; col++)
                level[row][col].setTile(Rotation.getRandom());
    }

    /**
     * Add a level to this game
     *
     * @param levelToAdd the array containing the pixels of the new level.
     */
    private void addLevel(Tile[][] levelToAdd) {
        levels.add(levelToAdd.clone());
    }

    /**
     * Checks if the user rotated the tiles to win the game.
     *
     * @param userChoices The user's choices of the tile rotations.
     * @return If the user has correctly completed the puzzle.
     */
    public boolean gameOver(Tile[][] userChoices) {
        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++)
                userChoices[i][j].visited = false;

        boolean correct = gameOver(0, 0, 3, userChoices);
        if (correct) {
            currentLevel++;
        }
        return correct;
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
        return (x == gridSize - 1 && y == gridSize - 1 && exits[1] == 1);
    }

    /**
     * Checks if the current coordinates are valid.
     */
    private boolean valid(int x, int y) {
        return (x < gridSize && y < gridSize && x >= 0 && y >= 0);
    }

    /**
     * Converts a char array (using ONLY chars 'T', 'I', 'X', 'L') to a tile array.
     *
     * @param input The input char array using 'T', 'I', 'X', and 'L'
     */
    private Tile[][] convertCharToTile(char[][] input) {
        Tile[][] output = new Tile[input.length][input.length];
        for (int i = 0; i < input.length; i++)
            for (int j = 0; j < input.length; j++)
                output[i][j] = TileFactory.createTile(input[i][j]);
        return output;
    }

}
