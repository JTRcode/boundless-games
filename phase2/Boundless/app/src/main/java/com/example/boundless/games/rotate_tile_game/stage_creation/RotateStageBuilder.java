package com.example.boundless.games.rotate_tile_game.stage_creation;

import com.example.boundless.games.rotate_tile_game.TileLevel;

/**
 * Builds a new stage for the Rotate Tile game
 */
public class RotateStageBuilder {
    //tells us how large of a stage to create
    int gridLength = 10;
    //signifies the difficulty, higher difficulty means fewer solutions
    DifficultyEnum difficulty = DifficultyEnum.EASY;

    /**
     * Set the grid length of the tile game
     *
     * @param gridLength The grid length of the game
     * @return The builder to build the game using
     */
    public RotateStageBuilder setGridLength(int gridLength) {
        this.gridLength = gridLength;
        return this;
    }

    /**
     * Set the difficulty of the tile game
     *
     * @param difficulty The difficulty to set the game to
     * @return The builder to build the game using
     */
    public RotateStageBuilder setDifficulty(DifficultyEnum difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    /**
     * Create the TileLevel
     * @return The new tile level created
     */
    public TileLevel makeLevel() {
        RandomLevelGeneration generator = new RandomLevelGeneration(this);
        return generator.construct();
    }

}
