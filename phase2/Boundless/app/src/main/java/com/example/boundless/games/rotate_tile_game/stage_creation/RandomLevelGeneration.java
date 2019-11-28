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
    private List<Integer> pathToFinish;

    /**
     * the logic behind creating a Tile Level object, called by the RotateStageBuilder
     *
     * @param builder The builder that builds the stage
     */
    public RandomLevelGeneration(RotateStageBuilder builder) {
        this.pathToFinish = new ArrayList<>();
        this.gridLength = builder.gridLength;
        this.difficulty = builder.difficulty;
        makeStage(new int[]{0, 0}, new int[]{gridLength - 1, gridLength - 1});
    }

    private void makeStage(int[] start, int[] end) {
        stage = new Tile[gridLength][gridLength];
        pathToFinish = (new FindSolution(start, end, gridLength, difficulty)).getPathToFinish();
        makePath(3, 1);
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
            calculateNewCoord(coord, exit);
        }
    }

    private Tile addPipe(int diff) {
        return TileFactory.createTile((diff % 2 == 0) ? TileEnum.I : TileEnum.L);
    }

    private void fillBlanks() {
        int size = stage.length;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (stage[i][j] == null)
                    stage[i][j] = TileFactory.createTile(TileEnum.ANY);
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
