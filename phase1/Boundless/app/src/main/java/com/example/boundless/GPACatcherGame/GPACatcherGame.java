package com.example.boundless.GPACatcherGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
//import java.awt.Graphics;

import com.example.boundless.Game;
import com.example.boundless.Panel;

/**
 * A GPA catcher game, where you catch falling objects to get a good grade!
 */
public class GPACatcherGame extends Game {

    private static double gpa; // current GPA
    private static int life; // current life remaining (max 3)
    private static int time; // current time remaining  (overall time to be determined)
    private static int bomb_avoided; // every 10 bombs avoided = +1 life
    //private Basket basket;
    private GPAManager manager;
    Paint paint = new Paint();

    public GPACatcherGame() {
        this(1);
    }

    public GPACatcherGame(int time) {

        GPACatcherGame.time = time;
        gpa = 3.0;
        life = 3;
        bomb_avoided = 0;
        manager = new GPAManager();
        manager.basket = new Basket(50);
        manager.addFallingObject();
    }

    static void addGpa(double gpa) {
        GPACatcherGame.gpa += gpa;
    }

    static void addLife(int life) {
        GPACatcherGame.life += life;
    }

    static void addTime(int time) {
        GPACatcherGame.time += time;
    }

    static void bomb_missed(){
        bomb_avoided += 1;
        if (bomb_avoided >= 10){
            life += 1;
        }
    }

    @Override
    public boolean gameOver() {
        return (time <= 0 || life <= 0);
    }

    @Override
    public void draw(Canvas canvas) {
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(24);

        canvas.drawText("GPA: " + gpa, 50, 50, paint);
        canvas.drawText("Time: " + time, Panel.SCREEN_WIDTH - 130, 50, paint);
        canvas.drawText("Life: " + life, Panel.SCREEN_WIDTH - 130, 80, paint);
        manager.basket.draw(canvas);
        manager.draw(canvas);
    }

    @Override
    public void screenTouched(int x, int y) {
        int mid = Panel.SCREEN_WIDTH / 2;
        if (x <= mid) {
            manager.basket.moveLeft();
        } else {
            manager.basket.moveRight();
        }
    }

    @Override
    public void update() {
        //TODO

        manager.addFallingObject();
        manager.update();

    }
}
