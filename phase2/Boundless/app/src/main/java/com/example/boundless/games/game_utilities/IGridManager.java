package com.example.boundless.games.game_utilities;

import java.util.List;

/**
 * A manager for a game with a grid.
 *
 * @param <T> The type of the user's choices.
 */
public interface IGridManager<T> {
    /**
     * Gets the grid size of the game.
     *
     * @return The grid size
     */
    int getGridSize();

    /**
     * Checks if the user's choices are correct
     *
     * @return If the choices are correct
     */
    boolean checkAnswer();

    /**
     * Get the user's choices for the grid
     *
     * @return The user's choices
     */
    T[][] getUserChoices();

    /**
     * Labels a level, if necessary
     *
     * @return The labels of the current level
     */
    List<List<Integer>> label();
}
