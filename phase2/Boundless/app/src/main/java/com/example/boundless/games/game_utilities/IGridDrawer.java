package com.example.boundless.games.game_utilities;

/**
 * A drawer for a game with a grid.
 */
public interface IGridDrawer {
    /**
     * Get the starting x position of the grid
     *
     * @return The starting x position of the grid
     */
    int getStartX();

    /**
     * Get the starting y position of the grid
     *
     * @return The starting y position of the grid
     */
    int getStartY();

    /**
     * Draw the game layout
     */
    void draw();
}
