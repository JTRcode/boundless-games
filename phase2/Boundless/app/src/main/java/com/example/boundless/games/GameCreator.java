package com.example.boundless.games;

import com.example.boundless.games.game_utilities.*;
import com.example.boundless.games.pixel_game.*;
import com.example.boundless.games.pixel_instructions.*;
import com.example.boundless.games.rotate_tile_game.*;
import com.example.boundless.games.rotate_tile_game.tiles.Tile;

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
                GridManager<PixelOptions, PixelLevel> pixelManager = new PixelManager(level);
                IGridDrawer pixelDrawer = new PixelDrawer(pixelManager);
                ITouchHandler pixelTouchHandler = new PixelTouchHandler(pixelManager);
                return gameBuilder.buildTouchHandler(pixelTouchHandler).buildDrawer(pixelDrawer)
                        .buildLevel(level).buildManager(pixelManager).buildGame(gameToCreate);
            case GPACATCHER:
                return new GPACatcherGameFacade(GameResources.GPA_MAX_TIME);
            case ROTATETILE:
                GridManager<Tile, TileLevel> tileManager = new TileManager(level);
                IGridDrawer tileDrawer = new RotateTileDrawer(tileManager);
                ITouchHandler tileTouchHandler = new RotateTileTouchHandler(tileManager);
                return gameBuilder.buildTouchHandler(tileTouchHandler).buildDrawer(tileDrawer)
                        .buildLevel(level).buildManager(tileManager).buildGame(gameToCreate);
            case PIXEL_INSTRUCTIONS:
                return createPixelInstructions(gameToCreate, level);
            default:
                return null;
        }
    }

    private Game createPixelInstructions(GamesEnum gameToCreate, int level) {
        GridManager<PixelOptions, PixelLevel> manager = new PixelManager(level);
        IGridDrawer drawer = new PixelInstructionDrawer(manager);
        ITouchHandler touchHandler = new PixelInstructionTouchHandler(manager);
        return gameBuilder.buildTouchHandler(touchHandler).buildDrawer(drawer)
                .buildLevel(level).buildManager(manager).buildGame(gameToCreate);
    }
}
