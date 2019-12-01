package com.example.boundless.games.game_utilities;

/**
 * A manager for the games
 *
 * @param <E> A representation of a level in the game
 */
public interface IGameManager<E> {

    /**
     * Checks if the game is over
     *
     * @return if the game is over
     */
    boolean checkGameOver();

    /**
     * Gets a representation of the current level
     *
     * @return The current level's representation.
     */
    E getLevel();

}
