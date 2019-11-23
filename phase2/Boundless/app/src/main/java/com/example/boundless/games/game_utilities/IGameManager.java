package com.example.boundless.games.game_utilities;

public interface IGameManager<T, E> {

    boolean checkAnswer();

    T getUserChoices();

    E getLevel();
}
