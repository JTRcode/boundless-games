package com.example.boundless.games.game_utilities;

public abstract class CatcherGameManager implements IGameManager {

    abstract public void addFallingObject();

    abstract public void caughtObject();

    abstract public void missedObject();

    abstract public boolean checkGameOver();




}
