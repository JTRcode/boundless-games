package com.example.boundless.games;

import android.os.AsyncTask;

import com.example.boundless.games.game_utilities.GridManager;
import com.example.boundless.games.game_utilities.IGameDrawer;
import com.example.boundless.games.game_utilities.IGameManager;
import com.example.boundless.games.game_utilities.ITouchHandler;
import com.example.boundless.games.gpa_catcher_game.StatusUpdater;

/**
 * Builds a game using given interface inputs
 */
class GameBuilder {
    private ITouchHandler touchHandler;
    private IGameDrawer drawer;
    private IGameManager manager;
    private StatusUpdater statusUpdater;
    private int level;

    /**
     * Store the touch handler for the game
     *
     * @param touchHandler The ITouchHandler for the game
     * @return The game builder
     */
    GameBuilder buildTouchHandler(ITouchHandler touchHandler) {
        this.touchHandler = touchHandler;
        return this;
    }

    /**
     * Store the drawer for the game
     *
     * @param drawer The IDrawer for the game
     * @return The game builder
     */
    GameBuilder buildDrawer(IGameDrawer drawer) {
        this.drawer = drawer;
        return this;
    }

    /**
     * Store the level for the game
     *
     * @param level The level number for the game
     * @return The game builder
     */
    GameBuilder buildLevel(int level) {
        this.level = level;
        return this;
    }

    //TODO create updater Interface
    GameBuilder buildStatusUpdater(StatusUpdater updater){
        this.statusUpdater = updater;
        return this;
    }


    /**
     * Store the touch handler for the game
     *
     * @param manager The GridManager for the game
     * @return The game builder
     */
    GameBuilder buildManager(IGameManager manager) {
        this.manager = manager;
        return this;
    }

    /**
     * Store the touch handler for the game
     *
     * @param game The GamesEnum for the game
     * @return An instance of the game
     */
    Game buildGame(GamesEnum game) {
        switch (game) {
            case PIXELS:
                if (touchHandler == null || drawer == null || manager == null) return null;
                return new PixelGameFacade(this);
            case PIXEL_INSTRUCTIONS:
                if (touchHandler == null || drawer == null || manager == null) return null;
                return new PixelInstructions(this);
            case ROTATETILE:
            case TILE_INSTRUCTIONS:
                if (touchHandler == null || drawer == null || manager == null) return null;
                return new RotateTileGameFacade(this);
            case GPACATCHER:
                if (touchHandler == null || drawer == null || manager == null || statusUpdater == null) return null;
                return new GPACatcherGameFacade(this);
            default:
                return null;
        }
    }

    /**
     * Get the touch handler for the game
     *
     * @return The ITouchHandler for the game
     */
    ITouchHandler getTouchHandler() {
        return touchHandler;
    }

    /**
     * Get the grid drawer for the game
     *
     * @return The IGameDrawer for the game
     */
    IGameDrawer getDrawer() {
        return drawer;
    }

    /**
     * Get the grid manager for the game
     *
     * @return The GridManager for the game
     */
    IGameManager getManager() {
        return manager;
    }

    StatusUpdater getStatusUpdater(){
        return statusUpdater;
    }
    /**
     * Get the level number for the game
     *
     * @return The level for the game
     */
    int getLevel() {
        return level;
    }
}
