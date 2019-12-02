package com.example.boundless.games.rotate_tile_game.tiles;

import android.graphics.BitmapFactory;

import com.example.boundless.Panel;
import com.example.boundless.R;

/**
 * A tile that looks like a T.
 */
class TTile extends Tile {
    /**
     * A new tile that looks like a T
     */
    TTile() {
        super(new int[]{0, 1, 1, 1}, BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.t_pipe));
    }
}