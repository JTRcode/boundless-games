package com.example.boundless.GPACatcherGame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.example.boundless.GPACatcherGame.GPAPanel;

public class GPAMainThread extends Thread{
    public static final int MAX_FPS = 30;
    private GPAPanel gpaPanel;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private boolean running;
    public static Canvas canvas;


    public GPAMainThread(SurfaceHolder surfaceHolder, GPAPanel gpaPanel){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gpaPanel = gpaPanel;
    }

    @Override
    public void run(){
        long startTime;
        long timeMills = 1000/MAX_FPS;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000/MAX_FPS;

        while(running){
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gpaPanel.update();
                    this.gpaPanel.draw(canvas);
                }
            }catch(Exception e){e.printStackTrace();}
            finally {
                if (canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch(Exception e){e.printStackTrace();}
                }
            }
            timeMills = (System.nanoTime() - startTime)/1000000;
            waitTime = targetTime - timeMills;
            try{
                if(waitTime > 0){
                    this.sleep(waitTime);
                }
            }catch(Exception e) {e.printStackTrace();}

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if(frameCount == MAX_FPS){
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(averageFPS);
            }
        }
    }
    public void setRunning(boolean running){
        this.running = running;
    }
}