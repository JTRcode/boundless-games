package com.example.boundless.games.gpa_catcher_game;

import com.example.boundless.Panel;
import com.example.boundless.games.game_utilities.CatcherGameManager;
import com.example.boundless.games.game_utilities.GameResources;

import com.example.boundless.games.gpa_catcher_game.catchers.Catcher;
import com.example.boundless.games.gpa_catcher_game.falling_objects.FallingObject;

/**
 * Creates new falling objects and updates them
 */
public class GPAGameManager extends CatcherGameManager {

    private GPAGameStatus status;

    /**
     * A new manager for the GPA game
     */
    public GPAGameManager() {
        status = new GPAGameStatus();
        status.getCatcher().setCoordX(Panel.SCREEN_WIDTH / 2);
        status.getCatcher().setCoordY(Panel.SCREEN_HEIGHT - 250);
    }

    @Override
    public GPAGameStatus getLevel() {
        return status;
    }

    @Override
    public boolean overlap(Catcher catcher, FallingObject object){
        int middle = object.getCoordX() + object.getSize() / 2;
        int bottom = object.getCoordY() + object.getSize();

        return (middle >= catcher.getCoordX() && middle <= catcher.getCoordX() + catcher.getSize() && bottom >= catcher.getCoordY());
    }

    @Override
    public String getGameOverText() {
        StringBuilder text = new StringBuilder(GameResources.getGPAGameOver());
        if (status.getTime() <= 0) text.append("You ran out of time!\n");
        if (status.getLives() <= 0) text.append("You ran out of lives!\n");
        text.append("Final GPA: ");
        text.append(Math.round(status.getGpa() * 100) / 100.0);
        return text.toString();
    }

    @Override
    public boolean checkGameOver() {
        return (status.getTime() < 0 || status.getLives() <= 0);
    }
}