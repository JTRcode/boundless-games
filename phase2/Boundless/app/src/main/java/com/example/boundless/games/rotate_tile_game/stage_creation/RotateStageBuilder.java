package com.example.boundless.games.rotate_tile_game.stage_creation;

import com.example.boundless.games.rotate_tile_game.TileLevel;

public class RotateStageBuilder {
    //tells us how large of a stage to create
    int gridLength = 10;
    //signifies the difficulty, higher difficulty means fewer solutions
    DifficultyEnum difficulty = DifficultyEnum.EASY;

    /**
     * sets the gridLength
     * @param gridLength
     * @return
     */
    public RotateStageBuilder setGridLength(int gridLength) {
        this.gridLength = gridLength;
        return this;
    }

    /**
     * sets the difficulty
     * @param difficulty
     * @return
     */
    public RotateStageBuilder setDifficulty(DifficultyEnum difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    /**
     * Returns the tile level from this builder
     * @return
     */
    public TileLevel makeLevel() {
        RandomLevelGeneration generator = new RandomLevelGeneration(this);
        return generator.construct();
    }

}
