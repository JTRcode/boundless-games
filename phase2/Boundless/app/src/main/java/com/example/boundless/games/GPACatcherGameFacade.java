package com.example.boundless.games;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.example.boundless.games.game_utilities.GameResources;
import com.example.boundless.stats.Achievements;
import com.example.boundless.utilities.DrawUtility;
import com.example.boundless.Panel;
import com.example.boundless.R;
import com.example.boundless.games.gpa_catcher_game.GPAManager;

/**
 * A GPA catcher game, where you catch falling objects to get a good grade!
 */
public class GPACatcherGameFacade extends Game {

    private  static double gpa ; // current GPA
    private  static int life ; // current life remaining (max 3)
    private static int time; // current time remaining  (overall time to be determined)
    private static int bombAvoided = 0; // every 10 bombs avoided = +1 life

    private static Bitmap heart;
    private static int heartSize = 60;
    private GPAManager manager;
    private int speed = 50;
    private int lastX = 0;

    public GPACatcherGameFacade(int time) {
        this.time = time;
        setGpa(2.0);
        setLife(3);
        setSpeed(20);
        manager = new GPAManager(speed);
        manager.addFallingObject();

        heart = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.heart);
        heart = Bitmap.createScaledBitmap(heart, heartSize, heartSize, true);
    }

    public static int getLife() {
        return life;
    }

    public void setLife(int life) {
        GPACatcherGameFacade.life = life;
        if (checkItems("life")){
            GPACatcherGameFacade.life+=1;
        }
    }


    public static double getGpa() {
        return gpa;
    }

    public  void setGpa(double gpa) {
        GPACatcherGameFacade.gpa = gpa;
        if(checkItems("gpa"))
            GPACatcherGameFacade.gpa +=0.5;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        if(checkItems("speed"))
            this.speed = this.speed*2;
    }

    /**
     *  if shopInventory has items then use them automatically
     */

    private boolean checkItems(String item){
        //TODO will add more details after shopInventory is completed
        // this if-else statement can also be in GPA shop. But I don't know what change
        // will be made in GPAshop
        if (item.equals("extraLife")){
            //if number of extralife >=1
            // number of extralife -=1;
            return true;
        }else if(item.equals("extraTime")){
            //if number of extraTime >=1
            // number of extraTime -=1;
            return true;
        }else if (item.equals("doubleSpeed")) {
            //if number of doubleSpeed >=1
            // number of doubleSpeed -=1;
            return true;
        }
        return false;
    }


    /**
     * Add to the total GPA
     *
     * @param gpa The amount to add to the GPA
     */
    public  static void addGpa(double gpa) {
        GPACatcherGameFacade.gpa += gpa;
        if (gpa > 4.0)
            GPACatcherGameFacade.gpa = 4.0;
        else if (GPACatcherGameFacade.gpa < 0)
        GPACatcherGameFacade.gpa = 0;
    }

    /**
     * Add to the total amount of lives
     *
     * @param life The number of lives to add
     */
    public static void addLife(int life) {
        GPACatcherGameFacade.life += life;
        if (GPACatcherGameFacade.life > 3)
            GPACatcherGameFacade.life = 3;
    }


    /**
     * Add to the total amount of time
     *
     * @param time The time to add to the game
     */
    public static void addTime(int time) {
        GPACatcherGameFacade.time += time;
        if (GPACatcherGameFacade.time > GameResources.GPA_MAX_TIME)
            GPACatcherGameFacade.time = GameResources.GPA_MAX_TIME;
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
        if (time <= 0 || life <= 0) {
            Achievements.gpaChecker(Math.round(gpa * 100) / 100.0);
            return true;
        }
        return false;
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
        if (time >= GameResources.GPA_MAX_TIME / 2) color = Color.GREEN;
        else if (time >=GameResources.GPA_MAX_TIME  / 4) color = Color.YELLOW;
        else color = Color.RED;
        DrawUtility.drawRectangle(new int[]{0, 10, time, 70}, color);
    }

    private void drawGPA() {
        double roundedGPA = Math.round(gpa * 100) / 100.0;
        DrawUtility.drawString("GPA: " + roundedGPA, 50, 125, Color.WHITE, 60);
    }

    private void drawLives() {
        for (int lives = 1; lives <= life; lives++) {
            DrawUtility.drawBitmap(heart, Panel.SCREEN_WIDTH - lives * heartSize, 140);
        }
    }

    @Override
    public void screenTouched(int x, int y) {
        if (Math.abs(lastX - x) < speed) return;
        lastX = x;

        if (x < manager.basket.getCoordX() + manager.basket.getSize() / 2)
            manager.basket.moveLeft();
        else
            manager.basket.moveRight();
    }

    @Override
    public void update() {
        time -= 2;
        manager.addFallingObject();
        manager.update();
    }

    @Override
    String getInstructions() {
        return "Tap on the left or right sides of the screen to move the character.\n" +
                "Try to get all the assignments and sleep you can get, but be careful of bombs!";
    }

    @Override
    String getGameOverText() {
        StringBuilder text = new StringBuilder("GAME OVER! \n");
        if (time <= 0) text.append("You ran out of time!\n");
        if (life <= 0) text.append("You ran out of lives!\n");
        text.append("Final GPA: ");
        text.append(Math.round(gpa * 100) / 100.0);
        return text.toString();
    }
}
