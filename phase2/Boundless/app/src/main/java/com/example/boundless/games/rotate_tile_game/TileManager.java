package com.example.boundless.games.rotate_tile_game;

import com.example.boundless.games.BusinessContext;
import com.example.boundless.games.GamesEnum;
import com.example.boundless.games.game_utilities.GridManager;
import com.example.boundless.games.rotate_tile_game.stage_creation.DifficultyEnum;
import com.example.boundless.games.rotate_tile_game.stage_creation.RotateStageBuilder;
import com.example.boundless.games.rotate_tile_game.tiles.Rotation;
import com.example.boundless.games.rotate_tile_game.tiles.Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * Sets up tiles and updates them
 */
public class TileManager extends GridManager<Tile, TileLevel> {
    private static List<TileLevel> allStageList = new ArrayList<>();
    private TileLevel currentLevel;
    private Tile[][] userChoices;
    private int levelToPlay;

    /**
     * Creates a new tile manager
     *
     * @param levelToPlay The level to manage
     */
    public TileManager(int levelToPlay) {
        this.levelToPlay = levelToPlay;
        if (levelToPlay < 3)
            loadStage(DifficultyEnum.EASY, 4);
        else if (levelToPlay < 9)
            loadStage(DifficultyEnum.MEDIUM, 7);
        else
            loadStage(DifficultyEnum.HARD, 10);
        randomizeTiles(userChoices);
    }

    /**
     * Handles creating and assigning currentLevel and userChoices
     */
    private void loadStage(DifficultyEnum difficulty, int gridLength) {
        if (levelToPlay != BusinessContext.getNumOfLevels(GamesEnum.ROTATETILE) - 1) {
            currentLevel = allStageList.get(levelToPlay);
        } else {
            RotateStageBuilder builder = new RotateStageBuilder();
            currentLevel = builder.setDifficulty(difficulty).setGridLength(gridLength).makeLevel();
            allStageList.add(currentLevel);
        }
        userChoices = currentLevel.getTiles();
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
    public boolean checkGameOver() {
        int gridSize = getGridSize();
        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++)
                userChoices[i][j].visited = false;
        if (gameOver(0, 0, 3, userChoices)) {
            addLevel();
            return true;
        } else {
            return false;
        }
    }

    /**
     * when it's the last game, add another game
     */
    private void addLevel() {
        if (levelToPlay == BusinessContext.getNumOfLevels(GamesEnum.ROTATETILE) - 1) {
            BusinessContext.addLevel(GamesEnum.ROTATETILE);
        }
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
