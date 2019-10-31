package com.example.boundless.rotate_tile_game.tiles;

import android.graphics.BitmapFactory;

import com.example.boundless.Panel;
import com.example.boundless.R;

/**
 * A tile that looks like an +.
 */
class CrossTile extends Tile {

    CrossTile() {
        super(new int[]{1, 1, 1, 1});
        originalImage = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.cross_pipe);
        rotatedImage = originalImage;
    }
}
