package com.example.boundless.rotate_tile_game;

import com.example.boundless.Panel;
import com.example.boundless.rotate_tile_game.tiles.*;

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
    private int startX = 100;
    private int startY = Panel.SCREEN_HEIGHT / 4;
    private int tileSize;

    /**
     * The current level being played.
     */
    private int currentLevel = 0;

    public TileManager(int size) {
        gridSize = size;
        levels = new ArrayList<>();
        tileSize = (Panel.SCREEN_WIDTH - 2 * startX) / size;
    }

    /**
     * Gets the number of levels we have
     *
     * @return The total number of levels
     */
    public int getNumOfLevels() {
        return levels.size();
    }

    /**
     * Creates a new tile based on the enum given
     *
     * @param type Enum of the tile to create
     * @return The new tile
     */
    private Tile createNewTile(TileEnum type) {
        Tile tile = TileFactory.createTile(type, tileSize);
        return tile;
    }

    int getGridSize() {
        return gridSize;
    }

    int getTileSize() {
        return tileSize;
    }

    int getStartX() {
        return startX;
    }

    int getStartY() {
        return startY;
    }

    Tile[][] getTileStage() {
        setUpTiles();
        gridSize = (levels.get(currentLevel)).length;
        tileSize = (Panel.SCREEN_WIDTH - 2 * startX) / gridSize;
        return levels.get(currentLevel);
    }

    /**
     * Initializes the tiles array and randomizes the rotation.
     */
    void setUpTiles() {
        switch (currentLevel) {
            case 0:
                setUpTilesEasy();
                break;
            case 1:
                setUpTilesMedium();
                break;
            case 2:
                setUpTilesHard();
                break;
            case 3:
                setUpTilesExpert();
                break;
            default:
                break;
        }
    }

    /**
     * Initializes the tiles array and randomizes the rotation (easy mode).
     */
    public void setUpTilesEasy() {
        gridSize = 4;

        char[][] easyLayout = {
                {'L', 'L', 'I', 'X'},
                {'L', 'I', 'I', 'L'},
                {'X', 'L', 'T', 'I'},
                {'L', 'I', 'I', 'L'}};

        Tile[][] easyTileLevel = this.convertCharToTile(easyLayout, 4);
        this.randomizeTiles(easyTileLevel);
        addLevel(easyTileLevel);
    }

    /**
     * Initializes the tiles array and randomizes the rotation (medium mode).
     */
    public void setUpTilesMedium() {
        gridSize = 6;

        char[][] mediumLayout = {
                {'L', 'L', 'I', 'X', 'L', 'X'},
                {'L', 'I', 'I', 'L', 'L', 'X'},
                {'X', 'L', 'T', 'I', 'L', 'X'},
                {'L', 'I', 'I', 'L', 'L', 'X'},
                {'L', 'I', 'I', 'L', 'L', 'X'},
                {'L', 'I', 'I', 'L', 'L', 'X'}};

        Tile[][] medTileLevel = this.convertCharToTile(mediumLayout, 6);
        this.randomizeTiles(medTileLevel);
        addLevel(medTileLevel);
    }

    /**
     * Initializes the tiles array and randomizes the rotation (hard mode).
     */
    public void setUpTilesHard() {
        gridSize = 8;

        char[][] hardLayout = {
                {'L', 'L', 'L', 'X', 'T', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I'},
                {'L', 'L', 'L', 'I', 'I', 'I', 'I', 'I'}};

        Tile[][] hardTileLevel = this.convertCharToTile(hardLayout, 8);
        this.randomizeTiles(hardTileLevel);
        addLevel(hardTileLevel);
    }

    /**
     * Initializes the tiles array and randomizes the rotation (expert mode), i.e. play all levels
     * and an expert stage.
     */
    public void setUpTilesExpert() {
        gridSize = 10;

        char[][] expertLayout = {
                {'L', 'L', 'L', 'X', 'T', 'I', 'I', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I', 'I', 'I'},
                {'L', 'L', 'L', 'I', 'I', 'I', 'I', 'I', 'I', 'I'}};

        Tile[][] expertTileLevel = this.convertCharToTile(expertLayout, 10);
        this.randomizeTiles(expertTileLevel);
        addLevel(expertTileLevel);
    }

    /**
     * Randomizes the tile rotation
     *
     * @param level the level to randomize the tiles of.
     */
    public void randomizeTiles(Tile[][] level) {
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
     * @param size  The size of the input array.
     * @return The corresponding tile array version of the char array.
     */
    public Tile[][] convertCharToTile(char[][] input, int size) {
        Tile[][] output = new Tile[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                int newSize = (Panel.SCREEN_WIDTH - 2 * startX) / size;
                output[i][j] = TileFactory.createTile(input[i][j], newSize);
            }
        return output;
    }

}
