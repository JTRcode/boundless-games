package com.example.boundless.games.game_utilities;

import com.example.boundless.games.gpa_catcher_game.Basket;
import com.example.boundless.games.gpa_catcher_game.falling_objects.FallingObject;

public abstract class CatcherGameManager<E> implements IGameManager<E> {

    abstract public boolean hitsGround(FallingObject object);

    abstract public boolean overlap(Basket basket, FallingObject object);

    abstract public double getScore();

    abstract public String getGameOverText();
}
