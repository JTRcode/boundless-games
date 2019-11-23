package com.example.boundless.games.rotate_tile_game.stage_creation;

import com.example.boundless.games.rotate_tile_game.TileEnum;
import com.example.boundless.games.rotate_tile_game.TileLevel;
import com.example.boundless.games.rotate_tile_game.tiles.Tile;
import com.example.boundless.games.rotate_tile_game.tiles.TileFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates random levels for the tile game.
 */
public class RandomLevelGeneration {
    private Tile[][] stage;
    //tells us how large of a stage to create
    private int gridLength;
    //signifies the difficulty, higher difficulty means fewer solutions
    private DifficultyEnum difficulty;
    //if True, there may be tiles that have no pipes, basically a unusable tile
    private boolean hasWall;
    //if True, the must use the special tile as part of their solution
    private boolean mustUseTile;
    private int[] start;
    private int[] end;
    private List<Integer> pathToFinish;
    private FindSolution findSolution;

    /**
     * the logic behind creating a Tile Level object, called by the RotateStageBuilder
     *
     * @param builder
     */
    public RandomLevelGeneration(RotateStageBuilder builder) {
        this.pathToFinish = new ArrayList<>();
        this.gridLength = builder.gridLength;
        this.difficulty = builder.difficulty;
        this.hasWall = builder.hasWall;
        this.mustUseTile = builder.mustUseTile;
        makeStage();
    }


    private void makeStage() {
        stage = new Tile[gridLength][gridLength];
        start = new int[]{0, 0};
        end = new int[]{gridLength - 1, gridLength - 1};
        findSolution = new FindSolution(start, end, gridLength, difficulty);
        pathToFinish = findSolution.getPathToFinish();
        System.out.println(pathToFinish);
        makePath(3, 1);
        if (hasWall) {
            placeWalls();
        }
        if (mustUseTile) {
            placeMustUseTile();
        }
        fillBlanks();
    }

    private void makePath(int entry, int lastExit) {
        int[] coord = {0, 0};
        int diff;
        int exit;
        for (int i = 0; i <= pathToFinish.size(); i++) {
            exit = lastExit;
            if (i != 0)
                entry = (pathToFinish.get(i - 1) + 2) % 4;
            if (i != pathToFinish.size())
                exit = pathToFinish.get(i);
            diff = Math.abs(entry - exit) % 4;

            stage[coord[1]][coord[0]] = addPipe(diff);
            findSolution.calculateNewCoord(coord, exit);
        }
    }

    private Tile addPipe(int diff) {
        return TileFactory.createTile((diff % 2 == 0) ? TileEnum.I : TileEnum.L);
    }

    private void placeWalls() {
        //not yet implemented
    }

    private void placeMustUseTile() {
        //not yet implemented
    }

    private void fillBlanks() {
        int size = stage.length;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (stage[i][j] == null)
                    //stage[i][j] = TileFactory.createTile(TileEnum.X);
                    stage[i][j] = TileFactory.createTile(TileEnum.ANY);
    }

    /**
     * Constructs a new tile level with a generated layout
     *
     * @return The new tile level.
     */
    TileLevel construct() {
        TileLevel newLevel = new TileLevel();
        newLevel.setTiles(stage);
        return newLevel;
    }

}
