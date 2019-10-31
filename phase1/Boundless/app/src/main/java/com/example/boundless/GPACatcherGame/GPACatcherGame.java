package com.example.boundless.GPACatcherGame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.example.boundless.DrawUtility;
import com.example.boundless.Game;
import com.example.boundless.Panel;
import com.example.boundless.R;

/**
 * A GPA catcher game, where you catch falling objects to get a good grade!
 */
public class GPACatcherGame extends Game {

    private static double gpa = 2.0; // current GPA
    private static int life = 3; // current life remaining (max 3)
    private static int time; // current time remaining  (overall time to be determined)
    private static int bombAvoided = 0; // every 10 bombs avoided = +1 life

    private static int maxTime = Panel.SCREEN_WIDTH;
    private static Bitmap heart;
    private static int heartSize = 60;
    private GPAManager manager;

    public GPACatcherGame(Context context) {
        this(context, maxTime);
    }

    public GPACatcherGame(Context context, int time) {
        super(context);

        GPACatcherGame.time = time;
        manager = new GPAManager();
        manager.basket = new Basket(50);
        manager.addFallingObject();

        heart = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.heart);
        heart = Bitmap.createScaledBitmap(heart, heartSize, heartSize, true);
    }

    /**
     * Add to the total GPA
     *
     * @param gpa The amount to add to the GPA
     */
    public static void addGpa(double gpa) {
        GPACatcherGame.gpa += gpa;
        if (GPACatcherGame.gpa > 4.0)
            GPACatcherGame.gpa = 4.0;
        else if (GPACatcherGame.gpa < 0)
            GPACatcherGame.gpa = 0;
    }

    /**
     * Add to the total amount of lives
     *
     * @param life The number of lives to add
     */
    public static void addLife(int life) {
        GPACatcherGame.life += life;
        if (GPACatcherGame.life > 3)
            GPACatcherGame.life = 3;
    }


    /**
     * Add to the total amount of time
     *
     * @param time The time to add to the game
     */
    public static void addTime(int time) {
        GPACatcherGame.time += time;
        if (GPACatcherGame.time > maxTime)
            GPACatcherGame.time = maxTime;
    }

    /**
     * Count the number of times a bomb has been missed and add lives if above a threshold
     */
    public static void bombMissed() {
        //TODO: move this to the bomb class, can make a static variable for bombAvoided and use the addLife() method
        bombAvoided += 1;
        if (bombAvoided >= 10) {
            life += 1;
            bombAvoided = 0;
        }
    }

    @Override
    public boolean gameOver() {
        return (time <= 0 || life <= 0);
    }

    @Override
    public void draw() {
        drawTimeBar();
        drawGPA();
        drawLives();

        manager.basket.draw();
        manager.draw();
    }

    private void drawTimeBar() {
        int color;
        if (time >= maxTime / 2) color = Color.GREEN;
        else if (time >= maxTime / 4) color = Color.YELLOW;
        else color = Color.RED;
        DrawUtility.drawRectangle(new int[] {0, 10, time, 70}, color);
    }

    private void drawGPA() {
        double roundedGPA = Math.round(gpa * 100) / 100.0;
        DrawUtility.drawString("GPA: " + roundedGPA, 50, 125, Color.WHITE, 60);
    }

    private void drawLives() {
        for (int lives = 1; lives <= life; lives++) {
            DrawUtility.drawBitmap(heart, Panel.SCREEN_WIDTH - lives * heartSize, 80);
        }
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
        time -= 2;
        manager.addFallingObject();
        manager.update();
    }
}
