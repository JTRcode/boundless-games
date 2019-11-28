package com.example.boundless.games.pixel_instructions;

import android.view.MotionEvent;

import com.example.boundless.games.game_utilities.GridManager;
import com.example.boundless.games.game_utilities.ITouchHandler;
import com.example.boundless.games.pixel_game.PixelLevel;
import com.example.boundless.games.pixel_game.PixelOptions;
import com.example.boundless.games.pixel_game.PixelTouchHandler;

/**
 * A touch handler for the pixel instructions
 */
public class PixelInstructionTouchHandler extends PixelTouchHandler implements ITouchHandler {

    /**
     * Create a new touch handler for the pixel game
     *
     * @param manager The manager for the pixel game
     */
    public PixelInstructionTouchHandler(GridManager<PixelOptions, PixelLevel> manager) {
        super(manager);
    }

    @Override
    public void screenTouched(MotionEvent event) {
        PixelInstructionDrawer.nextStep();
        if (!PixelInstructionDrawer.readyForUser()) return;
        super.screenTouched(event);
    }
}
