package com.example.boundless.games;

import com.example.boundless.games.pixel_instructions.PixelInstructionDrawer;
import com.example.boundless.users.UserAccountManager;

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
        gameLevels.put(GamesEnum.PIXELS, 6);
        gameLevels.put(GamesEnum.ROTATETILE, 1);
        gameLevels.put(GamesEnum.PIXEL_INSTRUCTIONS, -1);
        instructionsToGame.put(GamesEnum.PIXEL_INSTRUCTIONS, GamesEnum.PIXELS);
        instructionsToGame.put(GamesEnum.TILE_INSTRUCTIONS, GamesEnum.ROTATETILE);
    }

    /**
     * Add a level to the given game
     *
     * @param game The game to add a level to
     */
    public static void addLevel(GamesEnum game) {
        if (!needsLevels(game)) return;
        gameLevels.put(game, gameLevels.get(game) + 1);
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
        if (game == GamesEnum.ROTATETILE)
            return UserAccountManager.currentUser.getUnlocked(game) + 1;
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
     * Gets the corresponding instructions for a game, if it exists
     *
     * @param game The game to find the instructions enum for
     * @return The instructions enum, if it exists or the original enum otherwise
     */
    public static GamesEnum getInstructionsEnum(GamesEnum game) {
        for (Map.Entry entry : instructionsToGame.entrySet())
            if (entry.getValue() == game) return (GamesEnum) entry.getKey();
        return game;
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
