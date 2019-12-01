package com.example.boundless.games.game_utilities;

import com.example.boundless.games.gpa_catcher_game.catchers.Catcher;
import com.example.boundless.games.gpa_catcher_game.falling_objects.FallingObject;

/**
 * A manager for a game with falling objects
 *
 * @param <E> A representation of the status of the game
 */
public abstract class CatcherGameManager<E> implements IGameManager<E> {

    /**
     * Tells if a basket and a falling object are overlapping
     *
     * @param catcher The basket
     * @param object  The object to check
     * @return If the basket and object overlap
     */
    public abstract boolean overlap(Catcher catcher, FallingObject object);

    /**
     * Gets the text to show when the game is over
     *
     * @return The text to show when the game is over
     */
    public abstract String getGameOverText();
}
