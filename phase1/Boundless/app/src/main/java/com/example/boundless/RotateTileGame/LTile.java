package com.example.boundless.RotateTileGame;

import android.graphics.BitmapFactory;

import com.example.boundless.Panel;
import com.example.boundless.R;

/**
 * A tile that looks like an L.
 */
class LTile extends Tile {
    LTile() {
        super(new int[]{1, 1, 0, 0});
        image = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.l_pipe);
    }
}
