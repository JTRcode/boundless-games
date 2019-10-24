package com.example.boundless.RotateTileGame;

import android.graphics.BitmapFactory;

import com.example.boundless.Panel;
import com.example.boundless.R;

/**
 * A tile that looks like a T.
 */
class TTile extends Tile {
    TTile() {
        super(new int[]{1, 1, 1, 0});
        image = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.t_pipe);
    }
}
