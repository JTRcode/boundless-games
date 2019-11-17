package com.example.boundless.games.game_utilities;

import android.view.MotionEvent;

/**
 * Handles touches on the screen
 */
public interface ITouchHandler {
    /**
     * Handles a touch event on the screen
     *
     * @param event The event to handle
     */
    void screenTouched(MotionEvent event);
}
