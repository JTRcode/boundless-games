package com.example.boundless.games.gpa_catcher_game;

import com.example.boundless.Panel;
import com.example.boundless.games.game_utilities.CatcherGameManager;
import com.example.boundless.games.game_utilities.GameResources;

import com.example.boundless.games.gpa_catcher_game.catchers.Catcher;
import com.example.boundless.games.gpa_catcher_game.falling_objects.FallingObject;
import com.example.boundless.stats.Achievements;

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

    /**
     * Get the game status
     * @return The status
     */
    @Override
    public GPAGameStatus getLevel() {
        return status;
    }

    /**
     * Checks if the Falling object overlaps with the basket
     * @param catcher The basket
     * @param object  The object to check
     * @return a boolean telling if they overlap
     */
    @Override
    public boolean overlap(Catcher catcher, FallingObject object){
        int middle = object.getCoordX() + object.getSize() / 2;
        int bottom = object.getCoordY() + object.getSize();

        return (middle >= catcher.getCoordX() && middle <= catcher.getCoordX() + catcher.getSize() && bottom >= catcher.getCoordY());
    }

    /**
     * Returns text to be displayed for user when the game finishes
     * @return The text to show if the game is over
     */
    @Override
    public String getGameOverText() {
        StringBuilder text = new StringBuilder(GameResources.getGPAGameOver());
        if (status.getTime() <= 0) text.append("You ran out of time!\n");
        if (status.getLives() <= 0) text.append("You ran out of lives!\n");
        text.append("Final GPA: ");
        text.append(Math.round(status.getGpa() * 100) / 100.0);
        return text.toString();
    }

    /**
     * checks if either the time has run out or the player has lost all their life
     * @return If the game is over
     */
    @Override
    public boolean checkGameOver() {
        if (status.getTime() < 0 || status.getLives() <= 0){
            Achievements.gpaChecker(status.getGpa());
            return true;
        }
        return false;
    }
}