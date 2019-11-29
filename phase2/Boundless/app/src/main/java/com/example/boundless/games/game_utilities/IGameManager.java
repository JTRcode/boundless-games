package com.example.boundless.games.game_utilities;

public interface IGameManager<E>{

    boolean checkGameOver();

    /**
     * Gets a representation of the current level
     *
     * @return The current level's representation.
     */
    E getLevel();

}
