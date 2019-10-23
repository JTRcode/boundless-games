package com.example.boundless.RotateTileGame;

import android.content.res.Resources;
import android.util.Log;

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
    private int gridSize = 10;
    private int tileSize = 100; //TODO assign it depending on the width of the screen

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;



    /**
     * The current level being played.
     */
    private int currentLevel = 0;

    public TileManager() {
        levels = new ArrayList<>();
    }

    public TileManager(int size) {
        gridSize = size;
        levels = new ArrayList<>();
    }

    public Tile createNewTile(TileEnum type){
        Tile tile;
        switch (type){
            case I:
                tile = new StraightTile();
                break;
            case T:
                tile = new TTile();
                break;
            case L:
                tile = new LTile();
                break;
            case X:
                tile = new CrossTile();
                break;
            default:
                Log.e("TileManager","TRIED CREATING NEW TILE< BUT DID NOT PASS CORRECT ENUM TYPE");
                return null;
        }
        tile.resize(tileSize);
        return tile;
    }

    public int getGridSize(){
        return gridSize;
    }

    public Tile[][] getTileStage(){
        return levels.get(currentLevel);
    }
    /**
     * Initializes the tiles array and randomizes the rotation.
     */
    public void setUpTiles() {
        //TODO: hardcode and randomize tiles?
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
     * Draws the tiles on the screen
     */
    public void draw() {
        Tile[][] level = levels.get(currentLevel);
        System.out.println("--------------------------");
        for (int i = 0; i < gridSize; i++) {
            StringBuilder builder = new StringBuilder("/  ");
            for (int j = 0; j < gridSize; j++) {
                if (level[i][j] instanceof CrossTile) {
                    builder.append(" -|- ");
                } else if (level[i][j] instanceof StraightTile && (level[i][j].getRotation() == Rotation.NORTH || level[i][j].getRotation() == Rotation.SOUTH)) {
                    builder.append("  |  ");
                } else if (level[i][j] instanceof StraightTile) {
                    builder.append(" --- ");
                } else if (level[i][j] instanceof TTile && level[i][j].getRotation() == Rotation.NORTH) {
                    builder.append("  |- ");
                } else if (level[i][j] instanceof TTile && level[i][j].getRotation() == Rotation.EAST) {
                    builder.append(" -,- ");
                } else if (level[i][j] instanceof TTile && level[i][j].getRotation() == Rotation.SOUTH) {
                    builder.append(" -|  ");
                } else if (level[i][j] instanceof TTile) {
                    builder.append(" -`- ");
                } else if (level[i][j] instanceof LTile && level[i][j].getRotation() == Rotation.NORTH) {
                    builder.append("  |_ ");
                } else if (level[i][j] instanceof LTile && level[i][j].getRotation() == Rotation.EAST) {
                    builder.append("  ,- ");
                } else if (level[i][j] instanceof LTile && level[i][j].getRotation() == Rotation.SOUTH) {
                    builder.append(" -,  ");
                } else if (level[i][j] instanceof LTile) {
                    builder.append(" -`  ");
                }
            }
            builder.append("  /\n");
            System.out.println(builder);
        }
        System.out.println("--------------------------");
        //TODO: change to graphical interface when we have it
    }

    /**
     * Add a level to this game
     *
     * @param levelToAdd the array containing the pixels of the new level.
     */
    public void addLevel(Tile[][] levelToAdd) {
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
                    currentLevel++;
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
                switch (input[i][j]) {
                    case 'L':
                        output[i][j] = new LTile();
                        break;
                    case 'T':
                        output[i][j] = new TTile();
                        break;
                    case 'I':
                        output[i][j] = new StraightTile();
                        break;
                    case 'X':
                        output[i][j] = new CrossTile();
                        break;
                    default:
                        break;
                }
            }
        return output;
    }

}
