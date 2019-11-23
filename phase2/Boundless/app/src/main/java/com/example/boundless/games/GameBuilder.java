package com.example.boundless.games;

import com.example.boundless.games.game_utilities.GridManager;
import com.example.boundless.games.game_utilities.IGridDrawer;
import com.example.boundless.games.game_utilities.ITouchHandler;

/**
 * Builds a game using given interface inputs
 */
class GameBuilder {
    private ITouchHandler touchHandler;
    private IGridDrawer drawer;
    private GridManager manager;
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
    GameBuilder buildDrawer(IGridDrawer drawer) {
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

    /**
     * Store the touch handler for the game
     *
     * @param manager The GridManager for the game
     * @return The game builder
     */
    GameBuilder buildManager(GridManager manager) {
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
                if (touchHandler == null || drawer == null || manager == null) return null;
                return new RotateTileGameFacade(this);
            case GPACATCHER:
                if (touchHandler == null || drawer == null || manager == null) return null;
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
     * @return The IGridDrawer for the game
     */
    IGridDrawer getDrawer() {
        return drawer;
    }

    /**
     * Get the grid manager for the game
     *
     * @return The GridManager for the game
     */
    GridManager getManager() {
        return manager;
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
