package com.example.boundless.games.game_utilities;

/**
 * A manager for a game with a grid.
 *
 * @param <T> The type of the user's choices.
 * @param <E> The type of the Game's level.
 */
public interface IGridManager<T, E> {
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
     * Gets a representation of the current level
     *
     * @return The current level's representation.
     */
    E getLevel();
}
