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

    private GPAGameStatus level;

    private static final String TAG = "GPAGameManager";

    public GPAGameManager(){
        level = new GPAGameStatus();
        level.getCatcher().setCoordX(Panel.SCREEN_WIDTH / 2);
        level.getCatcher().setCoordY(Panel.SCREEN_HEIGHT - 250);
    }

    public GPAGameStatus getLevel(){
        return level;
    }

    @Override
    public boolean overlap(Catcher catcher, FallingObject object){
        int middle = object.getCoordX() + object.getSize() / 2;
        int bottom = object.getCoordY() + object.getSize();

        return (middle >= catcher.getCoordX() && middle <= catcher.getCoordX() + catcher.getSize() && bottom >= catcher.getCoordY());
    }

    @Override
    public double getScore(){
        return level.getGpa();
    }

    @Override
    public String getGameOverText() {
        StringBuilder text = GameResources.getGPAGameOver();
        if (level.getTime() <= 0) text.append("You ran out of time!\n");
        if (level.getLives() <= 0) text.append("You ran out of lives!\n");
        text.append("Final GPA: ");
        text.append(Math.round(level.getGpa() * 100) / 100.0);
        return text.toString();
    }

    @Override
    public boolean hitsGround(FallingObject object){
        return object.getCoordY()>= Panel.SCREEN_HEIGHT;
    }

    @Override
    public boolean checkGameOver() {
        return(level.getTime() < 0 || level.getLives() <= 0);
    }
}