package com.example.boundless.games.rotate_tile_game.tiles;

import android.graphics.BitmapFactory;

import com.example.boundless.Panel;
import com.example.boundless.R;

/**
 * A tile that looks like an I.
 */
class StraightTile extends Tile {
    /**
     * A new tile that looks like an I
     */
    StraightTile() {
        super(new int[]{1, 0, 1, 0}, BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.straight_pipe));
    }
}
