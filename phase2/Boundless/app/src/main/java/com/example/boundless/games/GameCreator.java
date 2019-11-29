package com.example.boundless.games;

import android.os.AsyncTask;

import com.example.boundless.games.game_utilities.*;
import com.example.boundless.games.gpa_catcher_game.GPAGameDrawer;
import com.example.boundless.games.gpa_catcher_game.GPAGameManager;
import com.example.boundless.games.gpa_catcher_game.GPAGameTouchHandler;
import com.example.boundless.games.gpa_catcher_game.StatusUpdater;
import com.example.boundless.games.pixel_game.*;
import com.example.boundless.games.pixel_instructions.*;
import com.example.boundless.games.rotate_tile_game.*;
import com.example.boundless.games.rotate_tile_game.tiles.Tile;
import com.example.boundless.games.rotate_tile_instructions.RotateTileInstructionDrawer;

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
                IGameDrawer pixelDrawer = new PixelDrawer(pixelManager);
                ITouchHandler pixelTouchHandler = new PixelTouchHandler(pixelManager);
                return gameBuilder.buildTouchHandler(pixelTouchHandler).buildDrawer(pixelDrawer)
                        .buildLevel(level).buildManager(pixelManager).buildGame(gameToCreate);
            case GPACATCHER:
                CatcherGameManager GPAGameManager = new GPAGameManager();
                IGameDrawer GPAGameDrawer = new GPAGameDrawer(GPAGameManager);
                ITouchHandler GPAGameTouchHandler = new GPAGameTouchHandler(GPAGameManager);
                StatusUpdater gpaGameStatus = new StatusUpdater(GPAGameManager);
                return gameBuilder.buildTouchHandler(GPAGameTouchHandler).buildDrawer(GPAGameDrawer)
                        .buildLevel(level).buildManager(GPAGameManager).buildStatusUpdater(gpaGameStatus).buildGame(gameToCreate);
            case ROTATETILE:
                GridManager<Tile, TileLevel> tileManager = new TileManager(level);
                IGameDrawer tileDrawer = new RotateTileDrawer(tileManager);
                ITouchHandler tileTouchHandler = new RotateTileTouchHandler(tileManager);
                return gameBuilder.buildTouchHandler(tileTouchHandler).buildDrawer(tileDrawer)
                        .buildLevel(level).buildManager(tileManager).buildGame(gameToCreate);
            case PIXEL_INSTRUCTIONS:
                return createPixelInstructions(gameToCreate, level);
            case TILE_INSTRUCTIONS:
                return createTileInstructions(gameToCreate, 0);
            default:
                return null;
        }
    }

    private Game createTileInstructions(GamesEnum gameToCreate, int level) {
        GridManager<Tile, TileLevel> tileManager = new TileManager(level);
        IGameDrawer tileDrawer = new RotateTileInstructionDrawer(tileManager);
        ITouchHandler tileTouchHandler = new RotateTileTouchHandler(tileManager);
        return gameBuilder.buildTouchHandler(tileTouchHandler).buildDrawer(tileDrawer)
                .buildLevel(level).buildManager(tileManager).buildGame(gameToCreate);
    }

    private Game createPixelInstructions(GamesEnum gameToCreate, int level) {
        GridManager<PixelOptions, PixelLevel> manager = new PixelManager(level);
        IGameDrawer drawer = new PixelInstructionDrawer(manager);
        ITouchHandler touchHandler = new PixelInstructionTouchHandler(manager);
        return gameBuilder.buildTouchHandler(touchHandler).buildDrawer(drawer)
                .buildLevel(level).buildManager(manager).buildGame(gameToCreate);
    }
}
