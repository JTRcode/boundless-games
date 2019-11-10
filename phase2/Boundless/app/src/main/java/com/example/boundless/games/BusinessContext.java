package com.example.boundless.games;

/**
 * Holds info about the games
 */
public class BusinessContext {

    /**
     * Tell if the game needs levels
     * @param game The game to check
     * @return If the game needs levels
     */
    public static boolean needsLevels(GamesEnum game){
        return (game == GamesEnum.PIXELS || game == GamesEnum.ROTATETILE);
    }

    /**
     * Gets the number of levels for a game
     * @param game The game to get the number of levels for
     * @return The number of levels
     */
    public static int getNumOfLevels(GamesEnum game){
        if (!needsLevels(game)) return 0;
        if (game == GamesEnum.ROTATETILE) return 4;
        else if (game == GamesEnum.PIXELS) return 4;
        return 0;
    }
}
