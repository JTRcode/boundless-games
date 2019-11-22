package com.example.boundless.games.rotate_tile_game.stage_creation;

import android.util.Log;

import com.example.boundless.games.rotate_tile_game.TileEnum;
import com.example.boundless.games.rotate_tile_game.TileLevel;
import com.example.boundless.games.rotate_tile_game.tiles.Tile;
import com.example.boundless.games.rotate_tile_game.tiles.TileFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
    private int[][] pastLocation;
    private List<Integer> pathToFinish;
    private static final String TAG = "RandomLevelGeneration";

    /**
     * the logic behind creating a Tile Level object, called by the RotateStageBuilder
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
        this.pastLocation = new int[gridLength][gridLength];
        findSolution();
        printPastLocation(this.pastLocation);
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

    private void findSolution() {
        path(new int[]{0, 0}, this.pastLocation.clone(),1);
    }

    private boolean isOnFinishGrid(int[] position) {
        return (position[0] == gridLength - 1 && position[1] == gridLength - 1);
    }

    private boolean isDifficultEnough(int steps) {
        int minMoves;
        switch (difficulty) {
            case HARD:
                minMoves = gridLength * 4;
                break;
            case MEDIUM:
                minMoves = gridLength * 3;
                break;
            case EASY:
            default:
                minMoves = 0;
                break;
        }
        return steps >= minMoves;
    }

    private boolean hasBeenToBefore(int[] position) {
        return pastLocation[position[1]][position[0]] == 1;
    }

    private void printPastLocation(int[][] pastLocation) {
        Log.d(TAG,"Grid of Past locations");
        for (int[] row : pastLocation) {
            for (int value : row) {
                System.out.print(value);
            }
            System.out.println("");
        }
        System.out.print(pathToFinish);
    }

    private boolean path(int[] location, int[][] pastLocation, int steps) {
        if (outOfRange(location) || hasBeenToBefore(location)) {
            return false;
        } else if (isOnFinishGrid(location) && isDifficultEnough(steps)) {
            pastLocation[location[1]][location[0]] = 1;
            this.pastLocation = pastLocation;
            return true;
        }
        pastLocation[location[1]][location[0]] = 1;
        printPastLocation(pastLocation);
        ArrayList<Integer> choices = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        Log.d(TAG, "Size of choices is +" + choices.size());
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(choices.size());
            int move = choices.get(index);
            Log.d(TAG, "MOVE is " + move);
            choices.remove(index);
            int[] newLocation = location.clone();
            calculateNewCoord(newLocation, move);
            if (path(newLocation, pastLocation.clone(),steps+1)) {
                pathToFinish.add(0, move);
                return true;
            }
        }
        return false;
    }

    private boolean outOfRange(int[] location) {
        for (int xy_position : location)
            if (xy_position >= gridLength || xy_position < 0) {
                return true;
            }
        return false;
    }

    private void makePath(int entry, int lastExit) {
        int[] coord = {0,0};
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
            calculateNewCoord(coord, exit);
        }
    }

    private void calculateNewCoord(int[] coord, int move) {
        switch (move) {
            case 0:
                coord[1] -= 1;
                break;
            case 1:
                coord[0] += 1;
                break;
            case 2:
                coord[1] += 1;
                break;
            case 3:
                coord[0] -= 1;
                break;
            default:
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
     * @return The new tile level.
     */
    TileLevel construct() {
        TileLevel newLevel = new TileLevel();
        newLevel.setTiles(stage);
        return newLevel;
    }

}
