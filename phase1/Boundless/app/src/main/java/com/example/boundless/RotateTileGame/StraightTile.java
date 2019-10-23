package com.example.boundless.RotateTileGame;

import android.graphics.BitmapFactory;

import com.example.boundless.Panel;
import com.example.boundless.R;

/**
 * A tile that looks like an I.
 */
class StraightTile extends Tile {
    StraightTile() {
        super(new int[]{1, 0, 1, 0});
        image = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.straight_pipe);
    }
    //TODO
}
