package com.example.boundless.games.game_utilities;

/**
 * A manager for a game with a grid.
 *
 * @param <T> The type of the user's choices.
 * @param <E> The type of the Game's level.
 */
public abstract class GridManager<T, E> implements  IGameManager{
    /**
     * Gets the grid size of the game.
     *
     * @return The grid size
     */
    public abstract int getGridSize();

    /**
     * Checks if the user's choices are correct
     *
     * @return If the choices are correct
     */
    @Override
    public abstract boolean checkAnswer();

    /**
     * Get the user's choices for the grid
     *
     * @return The user's choices
     */
    @Override
    public abstract T[][] getUserChoices();

    /**
     * Gets a representation of the current level
     *
     * @return The current level's representation.
     */
    @Override
     public abstract E getLevel();
}
