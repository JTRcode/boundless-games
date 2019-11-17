package com.example.boundless.games;

import com.example.boundless.games.game_utilities.IGridDrawer;
import com.example.boundless.games.game_utilities.IGridManager;
import com.example.boundless.games.game_utilities.ITouchHandler;
import com.example.boundless.games.pixel_game.PixelDrawer;
import com.example.boundless.games.pixel_game.PixelLevel;
import com.example.boundless.games.pixel_game.PixelManager;
import com.example.boundless.games.pixel_game.PixelOptions;
import com.example.boundless.games.pixel_game.PixelTouchHandler;

/**
 * Creates and builds each game
 */
public class GameCreator {
    private GameBuilder gameBuilder = new GameBuilder();

    /**
     * Creates a game
     *
     * @param gameToCreate The enum of the game to create
     * @return The instance of the new game created.
     */
    public Game createGame(GamesEnum gameToCreate) {
        return createGame(gameToCreate, 0);
    }

    /**
     * Creates a game
     *
     * @param gameToCreate The enum of the game to create
     * @param level        The level of the game to create.
     * @return The instance of the new game created.
     */
    public Game createGame(GamesEnum gameToCreate, int level) {
        switch (gameToCreate) {
            case PIXELS:
                IGridManager<PixelOptions, PixelLevel> manager = new PixelManager(level);
                IGridDrawer drawer = new PixelDrawer(manager);
                ITouchHandler touchHandler = new PixelTouchHandler(manager);
                return gameBuilder.buildTouchHandler(touchHandler).buildDrawer(drawer)
                        .buildLevel(level).buildManager(manager).buildGame(gameToCreate);
            case GPACATCHER:
                return new GPACatcherGame();
            case ROTATETILE:
                return new RotateTileGame(level);
            default:
                return null;
        }
    }
}
