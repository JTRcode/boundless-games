package com.example.boundless.games.game_utilities;

/**
 * A drawer for a game with a grid.
 *
 * @param <T> The type of the user's choices.
 */
public interface IGridDrawer<T> {
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
     * Get the width of each element in the grid
     *
     * @return The width of each element in the grid
     */
    int getWidth();

    /**
     * Draw the game layout
     */
    void draw();

    /**
     * Get the user's choices for the grid
     *
     * @return The user's choices
     */
    T[][] getUserChoices();
}
