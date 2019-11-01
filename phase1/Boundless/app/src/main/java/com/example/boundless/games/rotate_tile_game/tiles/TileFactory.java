package com.example.boundless.games.rotate_tile_game.tiles;

import com.example.boundless.games.rotate_tile_game.TileEnum;

/**
 * A factory for creating tiles.
 */
public class TileFactory {

    private TileFactory() {
    }

    /**
     * Creates a tile based on the Enum
     *
     * @param tileToCreate The char version of the tile to create
     * @return The new tile created
     */
    public static Tile createTile(char tileToCreate, int size) {
        return createTile(TileEnum.valueOf(tileToCreate), size);
    }

    /**
     * Creates a tile based on the Enum
     *
     * @param tileToCreate The enum of the tile to create
     * @return The new tile created
     */
    public static Tile createTile(TileEnum tileToCreate, int size) {
        Tile newTile;
        switch (tileToCreate) {
            case X:
                newTile = new CrossTile();
                break;
            case I:
                newTile = new StraightTile();
                break;
            case L:
                newTile = new LTile();
                break;
            default:
                newTile = new TTile();
                break;
        }
        newTile.resize(size);
        return newTile;
    }
}
