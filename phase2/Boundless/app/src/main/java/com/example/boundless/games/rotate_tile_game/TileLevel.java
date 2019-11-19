package com.example.boundless.games.rotate_tile_game;

import com.example.boundless.Panel;
import com.example.boundless.games.game_utilities.GameResources;
import com.example.boundless.games.rotate_tile_game.tiles.Tile;
import com.example.boundless.games.rotate_tile_game.tiles.TileFactory;

/**
 * A single level in the Tile game
 */
public class TileLevel {
    private Tile[][] level;
    private int width;

    /**
     * Set the tiles of the tile game
     *
     * @param tiles The characters of the tiles to set the level to (using 'T', 'I', 'X', 'L', 'A')
     */
    void setTiles(char[][] tiles) {
        level = convertCharToTile(tiles);
    }

    /**
     * Get the tiles of this level
     *
     * @return The tiles for this level
     */
    Tile[][] getTiles() {
        width = (width <= 0) ? getWidth(GameResources.TILE_START_X) : width;
        if (level[0][0] != null && level[0][0].rotatedImage.getHeight() != width)
            resizeUserChoices(width);
        return level;
    }

    /**
     * Get the width for each tile
     *
     * @param startX The starting x
     * @return The width of each pixel
     */
    int getWidth(int startX) {
        if (level == null) return 0;
        int newWidth = (Panel.SCREEN_WIDTH - 2 * startX) / getGridSize();
        if (width != newWidth) {
            width = newWidth;
            resizeUserChoices(newWidth);
        }
        return newWidth;
    }

    /**
     * Get the size of the tile grid
     *
     * @return The size of the tile grid
     */
    int getGridSize() {
        return level.length;
    }

    private Tile[][] convertCharToTile(char[][] input) {
        Tile[][] output = new Tile[input.length][input.length];
        for (int i = 0; i < input.length; i++)
            for (int j = 0; j < input.length; j++)
                output[i][j] = TileFactory.createTile(input[i][j]);
        return output;
    }

    private void resizeUserChoices(int width) {
        for (int i = 0; i < getGridSize(); i++)
            for (int j = 0; j < getGridSize(); j++)
                level[i][j].resize(width);
    }
}
