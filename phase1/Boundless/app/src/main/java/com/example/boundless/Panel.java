package com.example.boundless;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.boundless.PixelGame.PixelGame;

public class Panel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    public static int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    /**
     * The game contents
     */
    public Game game;


    public Panel(Context context, GamesEnum gameToPlay) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        switch (gameToPlay){
            case PIXELS:
                game = new PixelGame();
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
        float x = event.getX();
        float y = event.getY();
        for (int pointer = 0; pointer < pointerCount; pointer++) {
            System.out.printf("  POINTER: %d: (%f,%f)",
                    event.getPointerId(pointer), event.getX(pointer), event.getY(pointer));
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
