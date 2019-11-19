package com.example.boundless.games;

import com.example.boundless.games.pixel_instructions.PixelInstructionDrawer;

import java.util.HashMap;
import java.util.Map;

/**
 * Holds info about the games
 */
public class BusinessContext {
    private static Map<GamesEnum, Integer> gameLevels = new HashMap<GamesEnum, Integer>();
    private static Map<GamesEnum, GamesEnum> instructionsToGame = new HashMap<GamesEnum, GamesEnum>();

    private BusinessContext() {
    }

    private static void setup() {
        gameLevels.put(GamesEnum.PIXELS, 4);
        gameLevels.put(GamesEnum.ROTATETILE, 4);
        gameLevels.put(GamesEnum.PIXEL_INSTRUCTIONS, -1);
        instructionsToGame.put(GamesEnum.PIXEL_INSTRUCTIONS, GamesEnum.PIXELS);
    }

    /**
     * Tell if the game needs levels
     *
     * @param game The game to check
     * @return If the game needs levels
     */
    public static boolean needsLevels(GamesEnum game) {
        if (gameLevels.size() == 0) setup();
        return gameLevels.get(game) != null;
    }

    /**
     * Gets the number of levels for a game
     *
     * @param game The game to get the number of levels for
     * @return The number of levels
     */
    public static int getNumOfLevels(GamesEnum game) {
        if (!needsLevels(game)) return 0;
        return gameLevels.get(game);
    }

    /**
     * Tells if a game wants to recieve updates on a touch event
     *
     * @param game The game to check
     * @return If the game wants updates on a touch event
     */
    public static boolean subscribesToTouch(GamesEnum game) {
        if (game == GamesEnum.PIXEL_INSTRUCTIONS) {
            return PixelInstructionDrawer.readyForUser();
        }
        return (game == GamesEnum.PIXELS || game == GamesEnum.GPACATCHER);
    }

    /**
     * Tells if a game is a tutorial
     *
     * @param game The game to check
     * @return If the game is a tutorial
     */
    public static boolean isInstructions(GamesEnum game) {
        if (instructionsToGame.size() == 0) setup();
        return instructionsToGame.get(game) != null;
    }

    /**
     * Gets the regular game from the instructions game
     *
     * @param game The instructions game to get the regular game from
     * @return The regular game
     */
    public static GamesEnum getRegularLevel(GamesEnum game) {
        if (!isInstructions(game)) return game;
        return instructionsToGame.get(game);
    }
}
