package com.example.boundless.games.pixel_instructions;

import android.view.MotionEvent;

import com.example.boundless.games.game_utilities.GridManager;
import com.example.boundless.games.game_utilities.ITouchHandler;
import com.example.boundless.games.pixel_game.PixelLevel;
import com.example.boundless.games.pixel_game.PixelOptions;
import com.example.boundless.games.pixel_game.PixelTouchHandler;

public class PixelInstructionTouchHandler extends PixelTouchHandler implements ITouchHandler {
    GridManager<PixelOptions, PixelLevel> manager;

    /**
     * Create a new touch handler for the pixel game
     *
     * @param manager The manager for the pixel game
     */
    public PixelInstructionTouchHandler(GridManager<PixelOptions, PixelLevel> manager) {
        super(manager);
        this.manager = manager;
    }

    @Override
    public void screenTouched(MotionEvent event) {
        PixelInstructionDrawer.nextStep();
        if (!PixelInstructionDrawer.readyForUser()) return;
        super.screenTouched(event);
    }
}
