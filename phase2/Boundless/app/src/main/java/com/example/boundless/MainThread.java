package com.example.boundless;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * The thread that the panel runs on
 */
public class MainThread extends Thread {
    public static Canvas canvas;

    private static final int MAX_FPS = 30;
    private Panel panel;
    private SurfaceHolder surfaceHolder;
    private boolean running;
    private boolean update = true;


    public MainThread(SurfaceHolder surfaceHolder, Panel panel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.panel = panel;
    }

    @Override
    public void run() {
        long startTime;
        long waitTime;
        long targetTime = 1000 / MAX_FPS;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    if (update)
                        this.panel.update();
                    this.panel.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            waitTime = targetTime - (System.nanoTime() - startTime) / 1000000;
            try {
                if (waitTime > 0) {
                    this.sleep(waitTime);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Starts or stops the thread.
     *
     * @param running Whether to run the thread
     */
    void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * Sets if the thread should update the panel
     *
     * @param update Whether to update the panel
     */
    void setUpdate(boolean update) {
        this.update = update;
    }
}