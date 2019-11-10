package com.example.boundless.games;

public class BusinessContext {


    public static boolean needsLevels(GamesEnum game){
        return (game == GamesEnum.PIXELS || game == GamesEnum.ROTATETILE);
    }

    public static int getNumOfLevels(GamesEnum game){
        if (!needsLevels(game)) return 0;
        return 4;
    }
}
