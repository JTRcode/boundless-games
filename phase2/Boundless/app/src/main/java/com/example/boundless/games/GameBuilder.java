package com.example.boundless.games;

import com.example.boundless.games.game_utilities.IGridDrawer;
import com.example.boundless.games.game_utilities.IGridManager;
import com.example.boundless.games.game_utilities.ITouchHandler;

class GameBuilder {
    private ITouchHandler touchHandler;
    private IGridDrawer pixelDrawer;
    private IGridManager manager;
    private int level;

    GameBuilder buildTouchHandler(ITouchHandler touchHandler) {
        this.touchHandler = touchHandler;
        return this;
    }

    GameBuilder buildDrawer(IGridDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
        return this;
    }

    GameBuilder buildLevel(int level) {
        this.level = level;
        return this;
    }

    GameBuilder buildManager(IGridManager manager) {
        this.manager = manager;
        return this;
    }

    Game buildGame(GamesEnum game) {
        switch (game) {
            case PIXELS:
                if (touchHandler == null || pixelDrawer == null || manager == null) return null;
                return new PixelGame(this);
            case GPACATCHER:
            case ROTATETILE:
            default:
                return null;
        }
    }

    ITouchHandler getTouchHandler() {
        return touchHandler;
    }

    IGridDrawer getPixelDrawer() {
        return pixelDrawer;
    }

    IGridManager getManager() {
        return manager;
    }

    int getLevel() {
        return level;
    }
}
