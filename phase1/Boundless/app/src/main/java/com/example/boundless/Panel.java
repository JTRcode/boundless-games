package com.example.boundless;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.boundless.GPACatcherGame.GPACatcherGame;
import com.example.boundless.pixel_game.PixelGame;
import com.example.boundless.RotateTileGame.RotateTileGame;

public class Panel extends SurfaceView implements SurfaceHolder.Callback {
    //TODO: finish javadocs (including for class)
    /**
     * The main thread to run on
     */
    private MainThread thread;
    /**
     * The width of the screen
     */
    public static final int SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
    /**
     * The height of the screen.
     */
    public static final int SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
    /**
     * The game contents.
     */
    private Game game;
    /**
     * The panel
     */
    private static SurfaceView instance;

    public Panel(Context context) {
        super(context);
    }

    public Panel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Panel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Get the current game on this panel
     *
     * @return The game
     */
    Game getGame() {
        return game;
    }

    /**
     * Choose the game the panel displays
     *
     * @param gameToPlay The enum of the game to play
     */
    void chooseGame(Context context, GamesEnum gameToPlay) {
        setupPanel();
        setFocusable(true);
        switch (gameToPlay) {
            case PIXELS:
                game = new PixelGame(context);
                break;
            case GPACATCHER:
                game = new GPACatcherGame(context);
                break;
            case ROTATETILE:
                game = new RotateTileGame(context);
                break;
            default:
                break;
        }
    }

    /**
     * Setup the panel with a transparent background.
     */
    private void setupPanel() {
        instance = this;
        instance.setZOrderOnTop(true);
        SurfaceHolder holder = instance.getHolder();
        holder.addCallback(this);
        holder.setFormat(PixelFormat.TRANSPARENT);

        thread = new MainThread(getHolder(), this);
    }

    public static SurfaceView getPanel() {
        return instance;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int pointerCount = event.getPointerCount();
        Statistics.clickEvent();
        for (int pointer = 0; pointer < pointerCount; pointer++) {
            MotionEvent.PointerCoords coords = new MotionEvent.PointerCoords();
            event.getPointerCoords(pointer, coords);
            game.screenTouched((int) coords.x, (int) coords.y);
        }
        return super.onTouchEvent(event);
    }

    public void update() {
        game.update();
        if (game.checkGameOver()){
            thread.setRunning(false);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            game.draw(canvas);
        }
    }

}
