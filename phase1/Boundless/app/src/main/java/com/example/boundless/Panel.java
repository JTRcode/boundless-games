package com.example.boundless;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.boundless.GPACatcherGame.GPACatcherGame;
import com.example.boundless.PixelGame.PixelGame;
import com.example.boundless.RotateTileGame.RotateTileGame;

public class Panel extends SurfaceView implements SurfaceHolder.Callback {
    /**
     * The main thread to run on
     */
    private MainThread thread;
    /**
     * The width of the screen
     */
    public static final int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    /**
     * The height of the screen.
     */
    public static final int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    /**
     * The game contents.
     */
    public Game game;


    public Panel(Context context, GamesEnum gameToPlay) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        switch (gameToPlay) {
            case PIXELS:
                game = new PixelGame();
                break;
            case GPACATCHER:
                game = new GPACatcherGame();
                break;
            case ROTATETILE:
                game = new RotateTileGame();
                break;
            default:
                break;
            //TODO
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();
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
        for (int pointer = 0; pointer < pointerCount; pointer++) {
            MotionEvent.PointerCoords coords = new MotionEvent.PointerCoords();
            event.getPointerCoords(pointer, coords);
            game.screenTouched((int)coords.x, (int)coords.y);
        }
        return super.onTouchEvent(event);
    }

    public void update() {
        game.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            game.draw(canvas);
        }
    }
}
