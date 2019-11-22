package com.example.boundless.games.rotate_tile_game.stage_creation;

import com.example.boundless.games.rotate_tile_game.TileLevel;
import com.example.boundless.games.rotate_tile_game.tiles.Tile;

public class RotateStageBuilder {
    //tells us how large of a stage to create
    int gridLength = 10;
    //signifies the difficulty, higher difficulty means fewer solutions
    DifficultyEnum difficulty = DifficultyEnum.EASY;
    //if True, there may be tiles that have no pipes, basically a unusable tile
    boolean hasWall = false;
    //if True, the must use the special tile as part of their solution
    boolean mustUseTile = false;

    public RotateStageBuilder setGridLength(int gridLength) {
        this.gridLength = gridLength;
        return this;
    }

    public RotateStageBuilder setDifficulty(DifficultyEnum difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public RotateStageBuilder setHasWall(boolean hasWall) {
        this.hasWall = hasWall;
        return this;
    }

    public RotateStageBuilder setMustUseTile(boolean mustUseTile) {
        this.mustUseTile = mustUseTile;
        return this;
    }

    public TileLevel makeLevel() {
        RandomLevelGeneration generator = new RandomLevelGeneration(this);
        return generator.construct();
    }

}
