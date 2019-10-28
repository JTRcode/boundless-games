package com.example.boundless.GPACatcherGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
//import java.awt.Graphics;

import com.example.boundless.Game;
import com.example.boundless.Panel;
import com.example.boundless.Statistics;

/**
 * A GPA catcher game, where you catch falling objects to get a good grade!
 */
public class GPACatcherGame extends Game {

    private static double gpa; // current GPA
    private static int life; // current life remaining (max 3)
    private static int time; // current time remaining  (overall time to be determined)
    private static int bomb_avoided; // every 10 bombs avoided = +1 life
    private boolean gameFinished = false;
    private GPAManager manager;
    Paint paint = new Paint();

    public GPACatcherGame(Context context) {
        this(context,1000);
    }

    public GPACatcherGame(Context context, int time) {
        super(context);


        GPACatcherGame.time = time;
        gpa = 2.0;
        life = 3;
        bomb_avoided = 0;
        manager = new GPAManager();
        manager.basket = new Basket(50);
        manager.addFallingObject();
    }

    static void addGpa(double gpa) {
        GPACatcherGame.gpa += gpa;
        if(GPACatcherGame.gpa>4.0)
            GPACatcherGame.gpa = 4.0;
        else if(GPACatcherGame.gpa<0)
            GPACatcherGame.gpa = 0;
    }

    static void addLife(int life) {
        GPACatcherGame.life += life;
        if(GPACatcherGame.life>3)
            GPACatcherGame.life = 3;
    }


    static void addTime(int time) {
        GPACatcherGame.time += time;
        if(GPACatcherGame.time>60)
            GPACatcherGame.time = 60;
    }

    static void bomb_missed(){
        bomb_avoided += 1;
        if (bomb_avoided >= 10){
            life += 1;
            bomb_avoided = 0;
        }
    }


    @Override
    public boolean gameOver() {
        if (time <= 0 || life <= 0){
            gameFinished = true;
            Statistics.sumTotalScore();

        }

        return gameFinished;
    }

    @Override
    public boolean isGameFinished() {
        gameOver();
        return gameFinished;
    }

    @Override
    public void draw(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setTextSize(36);

        double roundedGPA = Math.round(gpa*100)/100.0;
        canvas.drawText("GPA: " + roundedGPA, 50, 50, paint);
        canvas.drawText("Time: " + time/10, Panel.SCREEN_WIDTH - 160, 50, paint);
        canvas.drawText("Life: " + life, Panel.SCREEN_WIDTH - 130, 80, paint);
        manager.basket.draw(canvas);
        manager.draw(canvas);

        if (isGameFinished()){
            paint.setTextSize(60);
            canvas.drawText("You GPA is " + roundedGPA, Panel.SCREEN_WIDTH/2-200, Panel.SCREEN_HEIGHT/2, paint);
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
        time--;
        manager.addFallingObject();
        manager.update();

    }
}
