package com.example.boundless.GPACatcherGame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
//import java.awt.Graphics;

import com.example.boundless.Game;
import com.example.boundless.Panel;
import com.example.boundless.R;
import com.example.boundless.Statistics;

/**
 * A GPA catcher game, where you catch falling objects to get a good grade!
 */
public class GPACatcherGame extends Game {

    private static double gpa; // current GPA
    private static int life; // current life remaining (max 3)
    private static int time; // current time remaining  (overall time to be determined)
    private static int bomb_avoided; // every 10 bombs avoided = +1 life

//    private static int max_time = 1000;
    private static int max_time = Panel.SCREEN_WIDTH;
    private static Bitmap heart;
    private static int heart_size = 60;
    private GPAManager manager;
    Paint paint = new Paint();

    public GPACatcherGame(Context context) {
        this(context, max_time);
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

        heart = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.heart);
        heart = Bitmap.createScaledBitmap(heart, heart_size, heart_size, true);
    }

    public static void addGpa(double gpa) {
        GPACatcherGame.gpa += gpa;
        if(GPACatcherGame.gpa>4.0)
            GPACatcherGame.gpa = 4.0;
        else if(GPACatcherGame.gpa<0)
            GPACatcherGame.gpa = 0;
    }

    public static void addLife(int life) {
        GPACatcherGame.life += life;
        if(GPACatcherGame.life>3)
            GPACatcherGame.life = 3;
    }


    public static void addTime(int time) {
        GPACatcherGame.time += time;
        if(GPACatcherGame.time>max_time)
            GPACatcherGame.time = max_time;
    }

    public static void bomb_missed(){
        bomb_avoided += 1;
        if (bomb_avoided >= 10){
            life += 1;
            bomb_avoided = 0;
        }
    }


    @Override
    public boolean gameOver() {
        return (time <= 0 || life <= 0);
    }

    @Override
    public void draw(Canvas canvas) {
        if (time >= max_time/2){
            paint.setColor(Color.GREEN);
        }
        else if (time >= max_time/4){
            paint.setColor(Color.YELLOW);
        }
        else {
            paint.setColor(Color.RED);
        }
        canvas.drawRect(0, 10, time, 70, paint);

        paint.setColor(Color.WHITE);
        paint.setTextSize(60);

        double roundedGPA = Math.round(gpa*100)/100.0;
        canvas.drawText("GPA: " + roundedGPA, 50, 125, paint);

        if (life >= 1){
            canvas.drawBitmap(heart, Panel.SCREEN_WIDTH - heart_size, 80, paint);
        }
        if (life >= 2){
            canvas.drawBitmap(heart, Panel.SCREEN_WIDTH - 2* heart_size, 80, paint);
        }

        if (life == 3){
            canvas.drawBitmap(heart, Panel.SCREEN_WIDTH - 3*heart_size, 80, paint);
        }
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
        time -= 2;
        manager.addFallingObject();
        manager.update();
    }
}
