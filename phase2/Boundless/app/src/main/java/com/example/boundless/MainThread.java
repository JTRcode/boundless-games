package com.example.boundless;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * The thread that the panel runs on
 */
public class MainThread extends Thread {
    public static final int MAX_FPS = 30;
    public static Canvas canvas;

    private Panel panel;
    private double averageFPS;
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
        long timeMills = 1000 / MAX_FPS;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000 / MAX_FPS;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            try {

                    if (update) {
                        canvas = this.surfaceHolder.lockCanvas();
                        synchronized (surfaceHolder) {
                            this.panel.update();

                            this.panel.draw(canvas);
                        }

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
            timeMills = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMills;
            try {
                if (waitTime > 0) {
                    this.sleep(waitTime);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == MAX_FPS) {
                averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(averageFPS);
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